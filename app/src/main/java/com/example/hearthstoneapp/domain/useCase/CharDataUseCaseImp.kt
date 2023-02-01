package com.example.hearthstoneapp.domain.useCase

import com.example.hearthstoneapp.data.repository.CharDataRepository

class CharDataUseCaseImp(private val repository: CharDataRepository) : CharDataUseCase {
    override fun getCharFiltersData() = repository.fetchCharFiltersData()
}