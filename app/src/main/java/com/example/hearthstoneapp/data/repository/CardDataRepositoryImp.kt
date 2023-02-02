package com.example.hearthstoneapp.data.repository

import com.example.hearthstoneapp.data.datasource.CardDataSource
import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

class CardDataRepositoryImp(private val dataSource: CardDataSource) : CardDataRepository {
    override fun fetchCardFiltersData(): Flow<InfoFilterEntity> = dataSource.fetchCardFiltersData()

    override fun getRacesFiltersData(raceName: String): Flow<List<CardByFilterEntity>> = dataSource.fetchRacesFiltersData(raceName)
}
