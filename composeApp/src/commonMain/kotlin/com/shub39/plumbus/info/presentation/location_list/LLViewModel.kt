package com.shub39.plumbus.info.presentation.location_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LLViewModel: ViewModel() {

    private val _state = MutableStateFlow(LLState())
    val state = _state.asStateFlow()

    fun action(action: LLAction) {
        viewModelScope.launch {
            when(action) {
                is LLAction.OnCharacterClick -> {}
                is LLAction.OnSearchQueryChange -> {}
                is LLAction.OnTabSelected -> {}
            }
        }
    }

}