package com.shub39.plumbus.info.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// character list view model
class CLViewModel: ViewModel() {

    private val _state = MutableStateFlow(CLState())
    val state = _state.asStateFlow()

    fun action(action: CLAction) {
        viewModelScope.launch {
            when(action) {
                is CLAction.OnCharacterClick -> {}

                is CLAction.OnSearchQueryChange -> {
                    _state.update {
                        it.copy(
                            searchQuery = action.query
                        )
                    }
                }

                is CLAction.OnTabSelected -> {
                    _state.update {
                        it.copy(
                            selectIndex = action.index
                        )
                    }
                }
            }
        }
    }

}