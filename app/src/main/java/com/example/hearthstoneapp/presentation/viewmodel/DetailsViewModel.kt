package com.example.hearthstoneapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import com.example.hearthstoneapp.presentation.DetailsUiState
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

    private val _uiState = MutableStateFlow<DetailsUiState>(
        DetailsUiState.Success(emptyList())
    )
    val uiState: StateFlow<DetailsUiState> get() = _uiState


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
            _uiState.value = DetailsUiState.Loading

            useCase.getRacesFiltersData(itemName)
                .catch { errorMessage ->
                    _uiState.value = DetailsUiState.Error(errorMessage)
                }
                .collect { cardsList ->
                    _uiState.value = DetailsUiState.Success(cardsList)
                }
        }
    }

    private fun getClassesList(itemName: String) {
        scope.launch {
            _uiState.value = DetailsUiState.Loading

            useCase.getClassesFiltersData(itemName)
                .catch { errorMessage ->
                    _uiState.value = DetailsUiState.Error(errorMessage)
                }
                .collect { cardsList ->
                    _uiState.value = DetailsUiState.Success(cardsList)
                }
        }
    }


    private fun getQualityList(itemName: String) {
        scope.launch {
            _uiState.value = DetailsUiState.Loading

            useCase.getQualityFiltersData(itemName)
                .catch { errorMessage ->
                    _uiState.value = DetailsUiState.Error(errorMessage)
                }
                .collect { cardsList ->
                    _uiState.value = DetailsUiState.Success(cardsList)
                }
        }
    }

    private fun getSetsList(itemName: String) {
        scope.launch {
            _uiState.value = DetailsUiState.Loading

            useCase.getSetsFiltersData(itemName)
                .catch { errorMessage ->
                    _uiState.value = DetailsUiState.Error(errorMessage)
                }
                .collect { cardsList ->
                    _uiState.value = DetailsUiState.Success(cardsList)
                }
        }
    }

    private fun getFactionsList(itemName: String) {
        scope.launch {
            _uiState.value = DetailsUiState.Loading

            useCase.getFactionsFiltersData(itemName)
                .catch { errorMessage ->
                    _uiState.value = DetailsUiState.Error(errorMessage)
                }
                .collect { cardsList ->
                    _uiState.value = DetailsUiState.Success(cardsList)
                }
        }
    }

    private fun getTypesList(itemName: String) {
        scope.launch {
            _uiState.value = DetailsUiState.Loading

            useCase.getTypesFiltersData(itemName)
                .catch { errorMessage ->
                    _uiState.value = DetailsUiState.Error(errorMessage)
                }
                .collect { cardsList ->
                    _uiState.value = DetailsUiState.Success(cardsList)
                }
        }
    }
}

enum class FiltersName(var valueType: String) {
    CLASSES("Classes"),
    RACES("Races"),
    TYPES("Types"),
    FACTIONS("Factions"),
    SETS("Sets"),
    QUALITIES("Qualities");
}