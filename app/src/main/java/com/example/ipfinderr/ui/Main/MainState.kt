package com.example.ipfinderr.ui.Main

import com.example.ipfinderr.domain.search.IpResult

sealed class MainState{
    object Default: MainState()
    object Loading: MainState()
    object NetworkError: MainState()
    object EmptyResults: MainState()
    data class Content(val ipResult: IpResult): MainState()
}
