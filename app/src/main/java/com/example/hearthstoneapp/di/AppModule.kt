package com.example.hearthstoneapp.di

import com.example.hearthstoneapp.presentation.viewmodel.CharViewModel
import com.example.hearthstoneapp.data.*
import com.example.hearthstoneapp.data.datasource.CharDataSource
import com.example.hearthstoneapp.data.datasource.CharDataSourceImp
import com.example.hearthstoneapp.data.repository.CharDataRepository
import com.example.hearthstoneapp.data.repository.CharDataRepositoryImp
import com.example.hearthstoneapp.domain.useCase.CharDataUseCase
import com.example.hearthstoneapp.domain.useCase.CharDataUseCaseImp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    factory<CharDataSource> { CharDataSourceImp(service = get()) }
    factory<CharDataRepository> { CharDataRepositoryImp(dataSource = get()) }
    factory<CharDataUseCase> { CharDataUseCaseImp(repository = get()) }
    viewModel { CharViewModel(useCase = get()) }

    factory<CharService> {
        get<Retrofit>()
            .create(CharService::class.java)
    }
}

val applicationModules =
    listOf(
        appModule, networkServiceModule
    )