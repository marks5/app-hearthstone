package com.example.hearthstoneapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailsViewModel(private val useCase: CardDataUseCase) : ViewModel() {

    private val _uiStateSuccess = MutableStateFlow(DetailsUiState.Success(emptyList()))
    val uiStateSuccess: StateFlow<DetailsUiState> = _uiStateSuccess

    private val _uiStateLoading = MutableStateFlow(DetailsUiState.Loading(false))
    val uiStateLoading: StateFlow<DetailsUiState> = _uiStateLoading

    private val _uiStateError = MutableStateFlow(DetailsUiState.Error(Throwable()))
    val uiStateError: StateFlow<DetailsUiState> = _uiStateError

    fun getRaceList(raceName: String) {
        viewModelScope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getRacesFiltersData(raceName)
                .catch { errorMessage ->
                    _uiStateLoading.value = DetailsUiState.Loading(false)
                    _uiStateError.value = DetailsUiState.Error(errorMessage)
                }
                .collect { charByRace ->
                    _uiStateLoading.value = DetailsUiState.Loading(false)
                    _uiStateSuccess.value = DetailsUiState.Success(charByRace)
                }
        }
    }
}

sealed class DetailsUiState {
    data class Success(val charByRaceList: List<CardByFilterEntity>) : DetailsUiState()
    data class Error(val error: Throwable) : DetailsUiState()
    data class Loading(val isLoading: Boolean) : DetailsUiState()
}