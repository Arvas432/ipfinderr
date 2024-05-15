package com.example.ipfinderr.ui.searchHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.core.view.isVisible
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.ActivitySearchHistoryBinding
import com.example.ipfinderr.domain.IpResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchHistoryActivity : AppCompatActivity() {
    private val viewModel by viewModel<SearchHistoryViewModel>()
    private lateinit var binding: ActivitySearchHistoryBinding
    private var results = ArrayList<IpResult>()
    private lateinit var adapter: SearchHistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SearchHistoryAdapter(results, viewModel)
        binding.historyRv.adapter = adapter
        viewModel.getScreenStateLiveData().observe(this){
            renderState(it)
        }
        viewModel.showHistory()
        val backBtn = findViewById<ImageButton>(R.id.back_btn)
        backBtn.setOnClickListener {
            finish()
        }
        binding.clearButton.setOnClickListener {
            viewModel.clearHistory()
        }
    }

    private fun renderDefault(){
        binding.historyRv.isVisible = true
        binding.clearButton.isVisible = false
    }
    private fun renderContent(content: List<IpResult>){
        binding.historyRv.isVisible = true
        binding.clearButton.isVisible = true
        results.clear()
        results.addAll(content)
        adapter.notifyDataSetChanged()
        binding.historyRv.isVisible = true
        Log.i("adapter", adapter.itemCount.toString())
    }
    private fun renderEmpty(){
      //  binding.historyRv.isVisible = false
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
}