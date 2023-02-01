package com.example.hearthstoneapp.data.repository

import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

interface CharDataRepository {
    fun fetchCharFiltersData(): Flow<InfoFilterEntity>
}