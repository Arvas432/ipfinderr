package com.example.ipfinderr.ui.Main

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ipfinderr.domain.IpInteractor
import com.example.ipfinderr.domain.IpSearchResult
import com.example.ipfinderr.domain.SearchHistoryInteractor
import com.example.ipfinderr.domain.SearchResultType

class MainActivityViewModel(private val ipInteractor: IpInteractor, private val searchHistoryInteractor: SearchHistoryInteractor): ViewModel() {
    private var screenStateLiveData = MutableLiveData<MainState>(MainState.Default)
    private var searchData: String = SEARCH_DEF
    private var lastSearch: String = SEARCH_DEF
    private val handler = Handler(Looper.getMainLooper())
    private val searchRunnable = Runnable {
        ipFindSearch(searchData)
    }
    fun searchDebounce() {
        handler.removeCallbacks(searchRunnable, SEARCH_RUNNABLE_TOKEN)
        handler.postDelayed(searchRunnable ,
            SEARCH_RUNNABLE_TOKEN,
            SEARCH_DEBOUNCE_DELAY
        )
    }
    fun setSearchData(input: String){
        searchData = input
    }
    fun getSearchData() = searchData
    fun getScreenStateLiveData(): LiveData<MainState> = screenStateLiveData
    fun immediateSearch(){
        handler.removeCallbacks(searchRunnable, SEARCH_RUNNABLE_TOKEN)
        ipFindSearch(searchData)
    }
    fun searchLast(){
        handler.removeCallbacks(searchRunnable, SEARCH_RUNNABLE_TOKEN)
        ipFindSearch(lastSearch)
    }
    private fun ipFindSearch(query: String) {
        ipInteractor.searchIp(query, object: IpInteractor.IpConsumer {
            override fun consume(searchResult: IpSearchResult) {
                Log.i("viewmodel", "consuming")
                handler.post {
                    searchHistoryInteractor.write(searchResult.ipResult)
                    when(searchResult.type){
                        SearchResultType.SUCCESS ->{
                            Log.i("viewmodel", "success")
                            renderState(MainState.Content(searchResult.ipResult))
                        }
                        SearchResultType.EMPTY -> {
                            Log.i("viewmodel", "empty")
                            renderState(MainState.EmptyResults)
                        }
                        SearchResultType.LOADING -> {
                            Log.i("viewmodel", "loading")
                            renderState(MainState.Loading)
                        }
                        SearchResultType.ERROR -> {
                            renderState(MainState.NetworkError)
                            lastSearch = query
                        }
                    }
                }

            }
        }
        )
    }

    private fun renderState(state: MainState){
        screenStateLiveData.postValue(state)
    }
    fun onDestroy(){
        handler.removeCallbacksAndMessages(null)
    }
    companion object {
        const val SEARCH_RUNNABLE_TOKEN = 1
        const val SEARCH_DEF = ""
        const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}