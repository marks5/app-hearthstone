package com.example.hearthstoneapp.data.datasource

import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

interface CharDataSource {
    fun fetchCharFiltersData(): Flow<InfoFilterEntity>
}