package com.example.ipfinderr.ui.searchHistory.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.domain.search.SearchHistoryInteractor
import com.example.ipfinderr.ui.searchHistory.state.SearchHistoryState

class SearchHistoryViewModel(private val searchHistoryInteractor: SearchHistoryInteractor):ViewModel() {
    private var screenStateLiveData = MutableLiveData<SearchHistoryState>(SearchHistoryState.Default)
    private val handler = Handler(Looper.getMainLooper())
    fun getScreenStateLiveData(): LiveData<SearchHistoryState> = screenStateLiveData

    init {
        showHistory()
    }
    fun clearHistory(){
        searchHistoryInteractor.clear()
        renderState(SearchHistoryState.EmptyHistory)
    }
    fun showHistory(){
        handler.removeCallbacksAndMessages(null)
        val history = searchHistoryInteractor.read()
        if(history.isNotEmpty()){
            renderState(SearchHistoryState.Results(history))
        }else{
            renderState(SearchHistoryState.EmptyHistory)
        }
    }
    fun writeToHistory(input: IpResult){
        searchHistoryInteractor.write(input)
    }
    private fun renderState(state: SearchHistoryState){
        screenStateLiveData.postValue(state)
    }
    fun onDestroy(){
        handler.removeCallbacksAndMessages(null)
    }
}