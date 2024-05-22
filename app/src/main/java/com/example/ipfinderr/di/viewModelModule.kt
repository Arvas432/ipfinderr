package com.example.ipfinderr.di

import com.example.ipfinderr.ui.Main.MainScreenViewModel
import com.example.ipfinderr.ui.searchHistory.SearchHistoryViewModel
import com.example.ipfinderr.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel {
       MainScreenViewModel(get(), get())
    }
    viewModel{
        SearchHistoryViewModel(get())
    }
    viewModel{
        SettingsViewModel(get())
    }
}