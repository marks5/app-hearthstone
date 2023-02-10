package com.example.hearthstoneapp.data.datasource

import com.example.hearthstoneapp.data.CardService
import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import com.example.hearthstoneapp.presentation.utils.call

class CardDataSourceImp(private val service: CardService) : CardDataSource {
    override fun fetchCardFiltersData() = call<InfoFilterEntity> {
        service.fetchCardAllFilters()
    }

    override fun fetchRacesFiltersData(raceName: String) = call<List<CardByFilterEntity>> {
        service.fetchRacesFiltersData(raceName)
    }

    override fun fetchClassesFiltersData(className: String) = call<List<CardByFilterEntity>> {
        service.fetchClassesFiltersData(className)
    }

    override fun fetchQualityFiltersData(qualityName: String) = call<List<CardByFilterEntity>> {
        service.fetchQualityFiltersData(qualityName)
    }

    override fun fetchSetsFiltersData(setsName: String) = call<List<CardByFilterEntity>> {
        service.fetchSetsFiltersData(setsName)
    }

    override fun fetchFactionsFiltersData(factionsName: String) = call<List<CardByFilterEntity>> {
        service.fetchFactionFiltersData(factionsName)
    }

    override fun fetchTypesFiltersData(typesName: String) = call<List<CardByFilterEntity>> {
        service.fetchTypesFiltersData(typesName)
    }
}