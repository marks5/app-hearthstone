package com.example.hearthstoneapp.presentation

import com.example.hearthstoneapp.domain.model.InfoFilterEntity

sealed class UiState {
    object Loading : UiState()
    data class Success(val info: InfoFilterEntity) : UiState()
    data class Error(val error: Throwable) : UiState()
}