package com.example.hearthstoneapp.presentation

import com.example.hearthstoneapp.domain.model.InfoFilterEntity

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val value: InfoFilterEntity) : MainUiState()
    data class Error(val error: Throwable) : MainUiState()
}