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

            coEvery { cardDataUseCase.getClassesFiltersData("Druid") } returns flow { cardByFilterEntityList }
            viewModelMocked.setItemName("Druid", FiltersName.CLASSES.valueType)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getClassesFiltersData("Druid") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Races chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getRacesFiltersData("Orc") } returns flow { cardByFilterEntityList }
            viewModelMocked.setItemName("Orc", FiltersName.RACES.valueType)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getRacesFiltersData("Orc") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Sets chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getSetsFiltersData("Basic") } returns flow { cardByFilterEntityList }
            viewModelMocked.setItemName("Basic", FiltersName.SETS.valueType)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getSetsFiltersData("Basic") }

        }

    @Test
    fun `Whenever Types chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getTypesFiltersData("Minion") } returns flow { cardByFilterEntityList }
            viewModelMocked.setItemName("Minion", FiltersName.TYPES.valueType)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getTypesFiltersData("Minion") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Factions chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getFactionsFiltersData("Horde") } returns flow { cardByFilterEntityList }
            viewModelMocked.setItemName("Horde", FiltersName.FACTIONS.valueType)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getFactionsFiltersData("Horde") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }

    @Test
    fun `Whenever Qualities chosen name filter should start and stop then return a CardByFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getQualityFiltersData("Rare") } returns flow { cardByFilterEntityList }
            viewModelMocked.setItemName("Rare", FiltersName.QUALITIES.valueType)
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getQualityFiltersData("Rare") }
            Truth.assertThat(viewModelMocked.uiStateLoading.value)
                .isEqualTo(DetailsUiState.Loading(true))
        }


    @Test
    fun `Whenever Classes chosen name filter and has success then return a CardByFilterEntity`() = runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = DetailsViewModel(cardDataUseCase, dispatcher)

            val cardByFilterEntityListMocked = listOf(
                CardByFilterEntity("https://d15f34w2p8l1cc.cloudfront.net/hearthstone/ee2e355e689f8c074216ba922616fed718e5d55211816bf58d95b1f37e7cbf8c.png")
            )

            coEvery { cardDataUseCase.getClassesFiltersData("Druid") } returns flow {
                emit(
                    cardByFilterEntityListMocked
                )
            }
            viewModelMocked.setItemName("Druid", FiltersName.CLASSES.valueType)
            Truth.assertThat(viewModelMocked.uiStateSuccess.value)
                .isEqualTo(DetailsUiState.Success(listOf()))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getClassesFiltersData("Druid") }
            Truth.assertThat(viewModelMocked.uiStateSuccess.value)
                .isEqualTo(DetailsUiState.Success(cardByFilterEntityListMocked))
        }
}