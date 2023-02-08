package com.example.hearthstoneapp.presentation.viewmodel

import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import com.google.common.truth.Truth.assertThat
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
class MainViewModelTest {

    private val cardDataUseCase = mockk<CardDataUseCase>()
    private val infoFilterEntity = mockk<InfoFilterEntity>()

    @Test
    fun `When main screen open should show InfoFilterEntity`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModelMocked = MainViewModel(cardDataUseCase, dispatcher)

        coEvery { cardDataUseCase.getCardFiltersData() } returns flow { infoFilterEntity }
        viewModelMocked.getInfo()
        assertThat(viewModelMocked.uiStateLoading.value).isEqualTo(InfoUiState.Loading(false))
        advanceUntilIdle()

        coVerify { cardDataUseCase.getCardFiltersData() }
        assertThat(viewModelMocked.uiStateLoading.value).isEqualTo(InfoUiState.Loading(true))
    }
}
