package com.example.hearthstoneapp.presentation

import com.example.hearthstoneapp.domain.model.CardByFilterEntity

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val value: List<CardByFilterEntity>) : DetailsUiState()
    data class Error(val error: Throwable) : DetailsUiState()
}