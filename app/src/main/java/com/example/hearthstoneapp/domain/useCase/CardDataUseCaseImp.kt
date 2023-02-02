package com.example.hearthstoneapp.domain.useCase

import com.example.hearthstoneapp.data.repository.CardDataRepository

class CardDataUseCaseImp(private val repository: CardDataRepository) : CardDataUseCase {
    override fun getCardFiltersData() = repository.fetchCardFiltersData()

    override fun getRacesFiltersData(raceName: String) = repository.getRacesFiltersData(raceName)
}