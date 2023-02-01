package com.example.hearthstoneapp.domain.useCase

import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

interface CharDataUseCase {
    fun getCharFiltersData(): Flow<InfoFilterEntity>
}