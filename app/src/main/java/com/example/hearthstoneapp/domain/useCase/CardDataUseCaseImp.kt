package com.example.hearthstoneapp.domain.useCase

import com.example.hearthstoneapp.data.repository.CardDataRepository

class CardDataUseCaseImp(private val repository: CardDataRepository) : CardDataUseCase {
    override fun getCardFiltersData() = repository.getCardFiltersData()

    override fun getRacesFiltersData(raceName: String) = repository.getRacesFiltersData(raceName)

    override fun getClassesFiltersData(className: String) =
        repository.getClassesFiltersData(className)

    override fun getQualityFiltersData(qualityName: String) =
        repository.getQualityFiltersData(qualityName)

    override fun getSetsFiltersData(setsName: String) = repository.getSetsFiltersData(setsName)

    override fun getFactionsFiltersData(factionsName: String) =
        repository.getFactionsFiltersData(factionsName)

    override fun getTypesFiltersData(typesName: String) = repository.getTypesFiltersData(typesName)

}