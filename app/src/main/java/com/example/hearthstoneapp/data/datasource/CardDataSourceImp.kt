package com.example.hearthstoneapp.data.datasource

import com.example.hearthstoneapp.data.CardService
import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class CardDataSourceImp(private val service: CardService) : CardDataSource {
    override fun fetchCardFiltersData() = call<InfoFilterEntity> {
        service.fetchCardAllFilters()
    }

    override fun fetchRacesFiltersData(raceName: String) = call<List<CardByFilterEntity>> {
        service.fetchRacesFiltersData(raceName)
    }
}

fun <T> call(block: suspend FlowCollector<T>.() -> T): Flow<T> = flow {
    emit(block())
}