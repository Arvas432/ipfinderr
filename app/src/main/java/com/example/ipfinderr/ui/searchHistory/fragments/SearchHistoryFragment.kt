package com.example.ipfinderr.ui.searchHistory.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.ipfinderr.databinding.FragmentSearchHistoryBinding
import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.ui.BindingFragment
import com.example.ipfinderr.ui.searchHistory.adapters.SearchHistoryAdapter
import com.example.ipfinderr.ui.searchHistory.state.SearchHistoryState
import com.example.ipfinderr.ui.searchHistory.viewmodel.SearchHistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchHistoryFragment : BindingFragment<FragmentSearchHistoryBinding>() {
    private val viewModel by viewModel<SearchHistoryViewModel>()
    private var results = ArrayList<IpResult>()
    private lateinit var adapter: SearchHistoryAdapter
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchHistoryBinding {
        return FragmentSearchHistoryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchHistoryAdapter(results, viewModel)
        binding.historyRv.adapter = adapter
        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner){
            renderState(it)
        }
        viewModel.showHistory()
        binding.clearButton.setOnClickListener {
            viewModel.clearHistory()
        }
    }
    private fun renderDefault(){
        binding.emptyHistoryIv.isVisible = false
        binding.emptyHistoryTv.isVisible = false
        binding.historyRv.isVisible = true
        binding.clearButton.isVisible = false
    }
    private fun renderContent(content: List<IpResult>){
        binding.emptyHistoryIv.isVisible = false
        binding.emptyHistoryTv.isVisible = false
        binding.historyRv.isVisible = true
        binding.clearButton.isVisible = true
        results.clear()
        results.addAll(content)
        adapter.notifyDataSetChanged()
        binding.historyRv.isVisible = true
        Log.i("adapter", adapter.itemCount.toString())
    }
    private fun renderEmpty(){
        binding.emptyHistoryIv.isVisible = true
        binding.emptyHistoryTv.isVisible = true
        binding.historyRv.isVisible = false
        binding.clearButton.isVisible = false
    }
    private fun renderState(state: SearchHistoryState) {
        when(state){
            is SearchHistoryState.Default -> renderDefault()
            is SearchHistoryState.EmptyHistory -> renderEmpty()
            is SearchHistoryState.Results -> {
                renderContent(state.content)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}