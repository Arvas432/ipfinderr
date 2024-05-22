package com.example.ipfinderr.ui.searchHistory.state

import com.example.ipfinderr.domain.search.IpResult

sealed class SearchHistoryState {
    object EmptyHistory: SearchHistoryState()
    object Default: SearchHistoryState()
    data class Results(val content: List<IpResult>) : SearchHistoryState()
}