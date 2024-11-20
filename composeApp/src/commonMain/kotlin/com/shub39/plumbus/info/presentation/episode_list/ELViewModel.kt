package com.shub39.plumbus.info.presentation.episode_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// episode list viewmodel
class ELViewModel: ViewModel() {

    private val _state = MutableStateFlow(ELState())
    val state = _state.asStateFlow()

    fun action(action: ELAction) {
        viewModelScope.launch {
            when(action) {
                is ELAction.OnCharacterClick -> {}
                is ELAction.OnSearchQueryChange -> {}
                is ELAction.OnTabSelected -> {}
            }
        }
    }

}