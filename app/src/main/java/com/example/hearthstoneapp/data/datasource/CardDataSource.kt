package com.example.hearthstoneapp.data.datasource

import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

interface CardDataSource {
    fun fetchCardFiltersData(): Flow<InfoFilterEntity>
    fun fetchRacesFiltersData(raceName: String): Flow<List<CardByFilterEntity>>
}