package com.jordansilva.vana.ui.screen.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(HomeUIState(loading = true))
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        _uiState.update {
            HomeUIState(
                user = User(
                    "Jordan",
                    "https://avatars.githubusercontent.com/u/1149526?s=400&u=a8aa7cda348ab6c9a59eed234bd106fa6c8a50b6&v=4"
                ),
                loading = false
            )
        }
    }

}

data class HomeUIState(val user: User? = null, val loading: Boolean = false)
data class User(val name: String, val photoUrl: String)