package com.am.screen_mainlist.presentation.utils

sealed class ScreenStates (
    val message: String? = null
) {
    data object Success : ScreenStates()
    class Error (message : String?) : ScreenStates(message)
    data object Loading : ScreenStates()
}