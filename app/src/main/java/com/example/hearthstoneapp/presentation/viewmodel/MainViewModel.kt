package com.example.hearthstoneapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class CharViewModel(private val useCase: CardDataUseCase) : ViewModel() {

    private val _uiStateSuccess = MutableStateFlow(InfoUiState.Success(InfoFilterEntity()))
    val uiStateSuccess: StateFlow<InfoUiState> = _uiStateSuccess

    private val _uiStateLoading = MutableStateFlow(InfoUiState.Loading(false))
    val uiStateLoading: StateFlow<InfoUiState> = _uiStateLoading

    private val _uiStateError = MutableStateFlow(InfoUiState.Error(Throwable()))
    val uiStateError: StateFlow<InfoUiState> = _uiStateError

    fun getInfo() {
        viewModelScope.launch {
            _uiStateLoading.value = InfoUiState.Loading(true)
            useCase.getCardFiltersData()
                .catch { errorMessage ->
                    _uiStateLoading.value = InfoUiState.Loading(false)
                    _uiStateError.value = InfoUiState.Error(errorMessage)
                }
                .collect { info ->
                    _uiStateLoading.value = InfoUiState.Loading(false)
                    _uiStateSuccess.value = InfoUiState.Success(info)
                }
        }
    }
}

sealed class InfoUiState {
    data class Success(val info: InfoFilterEntity) : InfoUiState()
    data class Error(val error: Throwable) : InfoUiState()
    data class Loading(val isLoading: Boolean) : InfoUiState()
}