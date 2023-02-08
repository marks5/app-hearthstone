package com.example.hearthstoneapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val useCase: CardDataUseCase,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val scope = CoroutineScope(ioDispatcher)

    private val _uiStateSuccess = MutableStateFlow(DetailsUiState.Success(emptyList()))
    val uiStateSuccess: StateFlow<DetailsUiState> = _uiStateSuccess

    private val _uiStateLoading = MutableStateFlow(DetailsUiState.Loading(false))
    val uiStateLoading: StateFlow<DetailsUiState> = _uiStateLoading

    private val _uiStateError = MutableStateFlow(DetailsUiState.Error(Throwable()))
    val uiStateError: StateFlow<DetailsUiState> = _uiStateError


    fun setItemName(itemName: String, FilterName: String) {
        when (FilterName) {
            FiltersName.CLASSES.valueType -> {
                getClassesList(itemName)
            }
            FiltersName.RACES.valueType -> {
                getRacesList(itemName)
            }
            FiltersName.TYPES.valueType -> {
                getTypesList(itemName)
            }
            FiltersName.FACTIONS.valueType -> {
                getFactionsList(itemName)
            }
            FiltersName.SETS.valueType -> {
                getSetsList(itemName)
            }
            FiltersName.QUALITIES.valueType -> {
                getQualityList(itemName)
            }
        }
    }

    private fun getRacesList(itemName: String) {
        scope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getRacesFiltersData(itemName)
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

    private fun getClassesList(itemName: String) {
        scope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getClassesFiltersData(itemName)
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


    private fun getQualityList(itemName: String) {
        scope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getQualityFiltersData(itemName)
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

    private fun getSetsList(itemName: String) {
        scope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getSetsFiltersData(itemName)
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

    private fun getFactionsList(itemName: String) {
        scope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getFactionsFiltersData(itemName)
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

    private fun getTypesList(itemName: String) {
        scope.launch {
            _uiStateLoading.value = DetailsUiState.Loading(true)
            useCase.getTypesFiltersData(itemName)
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