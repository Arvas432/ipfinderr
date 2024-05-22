package com.example.ipfinderr.di

import com.example.ipfinderr.ui.Main.viewmodel.MainScreenViewModel
import com.example.ipfinderr.ui.searchHistory.viewmodel.SearchHistoryViewModel
import com.example.ipfinderr.ui.settings.viewmodel.SettingsViewModel
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