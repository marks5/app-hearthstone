package com.example.hearthstoneapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.InfoHelper
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


    fun getFilterName() {
        when (InfoHelper.getInstance().getItemKeyClicked()) {
            FiltersName.CLASSES.valueType -> {
                getClassesList()
            }
            FiltersName.RACES.valueType -> {
                getRacesList()
            }
            FiltersName.TYPES.valueType -> {
                getTypesList()
            }
            FiltersName.FACTIONS.valueType -> {
                getFactionsList()
            }
            FiltersName.SETS.valueType -> {
                getSetsList()
            }
            FiltersName.QUALITIES.valueType -> {
                getQualityList()
            }
        }
    }

    private fun getRacesList() {
        viewModelScope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getRacesFiltersData(InfoHelper.getInstance().itemSelected)
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

    private fun getClassesList() {
        viewModelScope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getClassesFiltersData(InfoHelper.getInstance().itemSelected)
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


    private fun getQualityList() {
        viewModelScope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getQualityFiltersData(InfoHelper.getInstance().itemSelected)
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

    private fun getSetsList() {
        viewModelScope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getSetsFiltersData(InfoHelper.getInstance().itemSelected)
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

    private fun getFactionsList() {
        viewModelScope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getFactionsFiltersData(InfoHelper.getInstance().itemSelected)
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

    private fun getTypesList() {
        viewModelScope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getTypesFiltersData(InfoHelper.getInstance().itemSelected)
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
    data class Success(val cardsByFilter: List<CardByFilterEntity>) : DetailsUiState()
    data class Error(val error: Throwable) : DetailsUiState()
    data class Loading(val isLoading: Boolean) : DetailsUiState()
}

enum class FiltersName(var valueType: String) {
    CLASSES("Classes"),
    RACES("Races"),
    TYPES("Types"),
    FACTIONS("Factions"),
    SETS("Sets"),
    QUALITIES("Qualities");
}