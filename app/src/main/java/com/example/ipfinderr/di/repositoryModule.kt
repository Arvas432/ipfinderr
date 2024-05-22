package com.example.ipfinderr.di

import com.example.ipfinderr.domain.search.IpRepository
import com.example.ipfinderr.data.IpRepositoryImpl
import com.example.ipfinderr.data.localstorage.impl.SearchHistoryRepositoryImpl
import com.example.ipfinderr.data.settings.SettingsRepositoryImpl
import com.example.ipfinderr.domain.search.SearchHistoryRepository
import com.example.ipfinderr.domain.settings.SettingsRepository
import org.koin.dsl.module

val repositoryModule = module{
    single<IpRepository>{
        IpRepositoryImpl(get())
    }
    single<SearchHistoryRepository>{
        SearchHistoryRepositoryImpl(get())
    }
    single<SettingsRepository>{
        SettingsRepositoryImpl(get())
    }
}