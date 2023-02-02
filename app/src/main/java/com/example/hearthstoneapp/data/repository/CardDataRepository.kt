package com.example.hearthstoneapp.data.repository

import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

interface CardDataRepository {
    fun fetchCardFiltersData(): Flow<InfoFilterEntity>
    fun getRacesFiltersData(raceName: String): Flow<List<CardByFilterEntity>>
}