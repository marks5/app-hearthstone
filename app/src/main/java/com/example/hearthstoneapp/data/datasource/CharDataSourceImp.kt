package com.example.hearthstoneapp.data.datasource

import com.example.hearthstoneapp.data.CharService
import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class CharDataSourceImp(private val service: CharService) : CharDataSource {
    override fun fetchCharFiltersData() = call<InfoFilterEntity> {
        service.fetchCharFiltersData()
    }
}

fun <T> call(block: suspend FlowCollector<T>.() -> T): Flow<T> = flow {
    emit(block())
}