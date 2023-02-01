package com.example.hearthstoneapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import com.example.hearthstoneapp.domain.useCase.CharDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class CharViewModel(private val useCase: CharDataUseCase) : ViewModel() {
    private val _uiStateSuccess = MutableStateFlow(InfoStateUiState.Success(InfoFilterEntity()))
    val uiStateSuccess: StateFlow<InfoStateUiState> = _uiStateSuccess


    private val _uiStateError = MutableStateFlow(InfoStateUiState.Error(Throwable()))
    val uiStateError: StateFlow<InfoStateUiState> = _uiStateError

    init {
        viewModelScope.launch {
            useCase.getCharFiltersData()
                .catch { errorMessage ->
                    _uiStateError.value = InfoStateUiState.Error(errorMessage)
                }
                .collect { info ->
                    _uiStateSuccess.value = InfoStateUiState.Success(info)
                }
        }
    }

    fun getListInfoFilters(): List<String> =
        listOf("Classes", "Sets", "Types", "Factions", "Qualities", "Races", "Locales")

}

sealed class InfoStateUiState {
    data class Success(val info: InfoFilterEntity) : InfoStateUiState()
    data class Error(val error: Throwable) : InfoStateUiState()
}