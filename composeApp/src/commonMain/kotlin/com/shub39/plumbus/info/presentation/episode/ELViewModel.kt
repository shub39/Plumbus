package com.shub39.plumbus.info.presentation.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shub39.plumbus.core.domain.onError
import com.shub39.plumbus.core.domain.onSuccess
import com.shub39.plumbus.core.presentation.toUiText
import com.shub39.plumbus.info.domain.EpisodeRepo
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

// episode list viewmodel
class ELViewModel(
    private val dataSource: EpisodeRepo
): ViewModel() {

    private var searchJob: Job? = null
    private var savedJob: Job? = null
    private var favsJob: Job? = null

    private val _state = MutableStateFlow(ELState())
    val state = _state.asStateFlow()
        .onStart {
            observeSearch()
            observeSaved()
            observeFavs()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    fun action(action: ELAction) {
        viewModelScope.launch {
            when(action) {
                is ELAction.OnEpisodeClick -> {
                    _state.update {
                        it.copy(
                            currentEpisode = action.episode
                        )
                    }
                }

                is ELAction.OnSearchQueryChange -> {
                    _state.update {
                        it.copy(
                            searchQuery = action.query
                        )
                    }
                }

                is ELAction.OnTabSelected -> {
                    _state.update {
                        it.copy(
                            selectIndex = action.index
                        )
                    }
                }

                is ELAction.OnSetFav -> {
                    viewModelScope.launch {
                        dataSource.setFavEpisode(action.id)
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
                        searchJob = searchEpisode(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeSaved() {
        savedJob?.cancel()
        savedJob = dataSource
            .getEpisodes()
            .onEach { episodes ->
                _state.update {
                    it.copy(saved = episodes)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeFavs() {
        favsJob?.cancel()
        favsJob = dataSource
            .getFavEpisodes()
            .onEach { episodes ->
                _state.update {
                    it.copy(favs = episodes)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchEpisode(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        dataSource
            .searchEpisode(query)
            .onSuccess { searchResults ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = searchResults
                    )
                }

                searchResults.forEach { dataSource.addEpisode(it) }
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