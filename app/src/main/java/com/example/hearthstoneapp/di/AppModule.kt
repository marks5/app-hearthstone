package com.example.hearthstoneapp.di

import com.example.hearthstoneapp.presentation.viewmodel.CardViewModel
import com.example.hearthstoneapp.data.*
import com.example.hearthstoneapp.data.datasource.CardDataSource
import com.example.hearthstoneapp.data.datasource.CardDataSourceImp
import com.example.hearthstoneapp.data.repository.CardDataRepository
import com.example.hearthstoneapp.data.repository.CardDataRepositoryImp
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import com.example.hearthstoneapp.domain.useCase.CardDataUseCaseImp
import com.example.hearthstoneapp.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    factory<CardDataSource> { CardDataSourceImp(service = get()) }
    factory<CardDataRepository> { CardDataRepositoryImp(dataSource = get()) }
    factory<CardDataUseCase> { CardDataUseCaseImp(repository = get()) }
    viewModel { CardViewModel(useCase = get()) }
    viewModel { DetailsViewModel(useCase = get()) }

    factory<CardService> {
        get<Retrofit>()
            .create(CardService::class.java)
    }
}

val applicationModules =
    listOf(
        appModule, networkServiceModule
    )