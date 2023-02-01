package com.example.hearthstoneapp.data.repository

import com.example.hearthstoneapp.data.datasource.CharDataSource
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow

class CharDataRepositoryImp(private val dataSource: CharDataSource) : CharDataRepository {
    override fun fetchCharFiltersData(): Flow<InfoFilterEntity> = dataSource.fetchCharFiltersData()
}
