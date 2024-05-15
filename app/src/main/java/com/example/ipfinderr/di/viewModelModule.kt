package com.example.ipfinderr.di

import com.example.ipfinderr.ui.Main.MainActivityViewModel
import com.example.ipfinderr.ui.searchHistory.SearchHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel {
       MainActivityViewModel(get(), get())
    }
    viewModel{
        SearchHistoryViewModel(get())
    }
}