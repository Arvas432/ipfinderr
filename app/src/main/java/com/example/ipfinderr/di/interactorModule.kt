package com.example.ipfinderr.di

import com.example.ipfinderr.domain.IpInteractor
import com.example.ipfinderr.domain.SearchHistoryInteractor
import com.example.ipfinderr.domain.impl.IpInteractorImpl
import com.example.ipfinderr.domain.impl.SearchHistoryInteractorImpl
import com.example.ipfinderr.domain.settings.SettingsInteractor
import com.example.playlistmaker.domain.settings.impl.SettingsInteractorImpl
import org.koin.dsl.module


val interactorModule = module {
    factory<IpInteractor>{
        IpInteractorImpl(get())
    }
    factory<SearchHistoryInteractor>{
        SearchHistoryInteractorImpl(get())
    }
    factory<SettingsInteractor>{
        SettingsInteractorImpl(get())
    }
}