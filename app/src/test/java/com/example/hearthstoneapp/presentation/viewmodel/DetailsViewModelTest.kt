package com.example.hearthstoneapp.presentation.viewmodel

import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    private val cardDataUseCase = mockk<CardDataUseCase>()
    private val cardByFilterEntityList = mockk<List<CardByFilterEntity>>()

    @Test
    fun `Whenever Classes chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getClassesFiltersData(FiltersName.CLASSES.name) } returns flow { cardByFilterEntityList }
            viewModelMocked.setFilterName(FiltersName.CLASSES.name)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getClassesFiltersData(FiltersName.CLASSES.name) }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Races chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getRacesFiltersData(FiltersName.RACES.name) } returns flow { cardByFilterEntityList }
            viewModelMocked.setFilterName(FiltersName.RACES.name)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getRacesFiltersData(FiltersName.RACES.name) }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Sets chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getSetsFiltersData(FiltersName.SETS.name) } returns flow { cardByFilterEntityList }
            viewModelMocked.setFilterName(FiltersName.SETS.name)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getSetsFiltersData(FiltersName.SETS.name) }

        }

    @Test
    fun `Whenever Types chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getTypesFiltersData("Types") } returns flow { cardByFilterEntityList }
            viewModelMocked.setFilterName("Types")
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getTypesFiltersData("Types") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Factions chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getFactionsFiltersData("Factions") } returns flow { cardByFilterEntityList }
            viewModelMocked.setFilterName("Factions")
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getFactionsFiltersData("Factions") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Qualities chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getQualityFiltersData("Qualities") } returns flow { cardByFilterEntityList }
            viewModelMocked.setFilterName("Qualities")
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getQualityFiltersData("Qualities") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }


    @Test
    fun `Whenever Classes chosen name filter and has success then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            val cardByFilterEntityListMocked = listOf(
                CardByFilterEntity("https://d15f34w2p8l1cc.cloudfront.net/hearthstone/ee2e355e689f8c074216ba922616fed718e5d55211816bf58d95b1f37e7cbf8c.png")
            )

            coEvery { cardDataUseCase.getClassesFiltersData(FiltersName.CLASSES.name) } returns flow {
                emit(
                    cardByFilterEntityListMocked
                )
            }
            viewModelMocked.setFilterName(FiltersName.CLASSES.name)
            Truth.assertThat(viewModelMocked.uiStateSuccess.value)
                .isEqualTo(DetailsUiState.Success(listOf()))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getClassesFiltersData(FiltersName.CLASSES.name) }
            Truth.assertThat(viewModelMocked.uiStateSuccess.value)
                .isEqualTo(DetailsUiState.Success(cardByFilterEntityListMocked))
        }
}