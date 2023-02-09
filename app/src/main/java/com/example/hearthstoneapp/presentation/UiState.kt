package com.example.hearthstoneapp.presentation

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val value: T) : UiState<T>()
    data class Error(val error: Throwable) : UiState<Nothing>()
}