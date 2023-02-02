package com.example.hearthstoneapp.domain.useCase

import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

interface CardDataUseCase {
    fun getCardFiltersData(): Flow<InfoFilterEntity>
    fun getRacesFiltersData(raceName: String): Flow<List<CardByFilterEntity>>
}