package com.example.ipfinderr.di

import com.example.ipfinderr.domain.IpRepository
import com.example.ipfinderr.data.IpRepositoryImpl
import com.example.ipfinderr.data.localstorage.impl.SearchHistoryRepositoryImpl
import com.example.ipfinderr.domain.SearchHistoryRepository
import org.koin.dsl.module

val repositoryModule = module{
    single<IpRepository>{
        IpRepositoryImpl(get())
    }
    single<SearchHistoryRepository>{
        SearchHistoryRepositoryImpl(get())
    }
}