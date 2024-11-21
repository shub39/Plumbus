package com.shub39.plumbus.info.presentation.location_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.plumbus.core.domain.onError
import com.shub39.plumbus.core.domain.onSuccess
import com.shub39.plumbus.core.presentation.toUiText
import com.shub39.plumbus.info.domain.LocationRepo
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LLViewModel(
    private val dataSource: LocationRepo
): ViewModel() {

    private var searchJob: Job? = null
    private var savedJob: Job? = null

    private val _state = MutableStateFlow(LLState())
    val state = _state.asStateFlow()
        .onStart {
            observeSearch()
            observeSaved()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    fun action(action: LLAction) {
        viewModelScope.launch {
            when(action) {
                is LLAction.OnLocationClick -> {}

                is LLAction.OnSearchQueryChange -> {
                    _state.update {
                        it.copy(
                            searchQuery = action.query
                        )
                    }
                }

                is LLAction.OnTabSelected -> {
                    _state.update {
                        it.copy(
                            selectIndex = action.index
                        )
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearch() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(errorMessage = null)
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchLocation(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeSaved() {
        savedJob?.cancel()
        savedJob = dataSource
            .getLocations()
            .onEach { locations ->
                _state.update {
                    it.copy(saved = locations)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchLocation(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        dataSource
            .searchLocation(query)
            .onSuccess { searchResults->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = searchResults
                    )
                }

                searchResults.forEach { dataSource.addLocation(it) }
            }
            .onError { searchError ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        searchResults = emptyList(),
                        errorMessage = searchError.toUiText()
                    )
                }
            }
    }

}