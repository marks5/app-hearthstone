package com.example.hearthstoneapp.data

import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import retrofit2.http.GET

interface CharService {

    @GET("info")
    suspend fun fetchCharFiltersData(): InfoFilterEntity
}
