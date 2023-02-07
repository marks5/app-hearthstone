package com.example.hearthstoneapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: CardDataUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val scope = CoroutineScope(ioDispatcher)

    private val _uiStateSuccess = MutableStateFlow(InfoUiState.Success(InfoFilterEntity()))
    val uiStateSuccess: StateFlow<InfoUiState> = _uiStateSuccess

    private val _uiStateLoading = MutableStateFlow(InfoUiState.Loading(false))
    val uiStateLoading: StateFlow<InfoUiState> = _uiStateLoading

    private val _uiStateError = MutableStateFlow(InfoUiState.Error(Throwable()))
    val uiStateError: StateFlow<InfoUiState> = _uiStateError

    private var infoFilterLis: Map<String, List<String>> = emptyMap()

    fun getInfo() {
        scope.launch {
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


    private fun getListInfoFilters(): List<String> =
        listOf("Classes", "Sets", "Types", "Factions", "Qualities", "Races", "Locales")

    fun setInfoResult(info: InfoFilterEntity) {

        val localesList = listOf(
            info.locales?.deDe,
            info.locales?.enGB,
            info.locales?.enUs,
            info.locales?.esEs,
            info.locales?.esMX,
            info.locales?.frFr,
            info.locales?.itIt,
            info.locales?.plPl,
            info.locales?.ruRu,
            info.locales?.zhTw,
            info.locales?.jaJp,
            info.locales?.thTh
        )

        infoFilterLis = mapOf(
            Pair(getListInfoFilters()[0], info.classes),
            Pair(getListInfoFilters()[1], info.sets),
            Pair(getListInfoFilters()[2], info.types),
            Pair(getListInfoFilters()[3], info.factions),
            Pair(getListInfoFilters()[4], info.qualities),
            Pair(getListInfoFilters()[5], info.races),
            Pair(getListInfoFilters()[6], localesList)
        ) as Map<String, List<String>>
    }

    fun getCardInfoList(): Map<String, List<String>> {
        return infoFilterLis
    }
}

sealed class InfoUiState {
    data class Success(val info: InfoFilterEntity? = null) : InfoUiState()
    data class Error(val error: Throwable) : InfoUiState()
    data class Loading(val isLoading: Boolean) : InfoUiState()
}