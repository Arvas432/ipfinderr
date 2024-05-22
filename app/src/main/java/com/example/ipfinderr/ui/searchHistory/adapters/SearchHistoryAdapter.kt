package com.example.ipfinderr.ui.searchHistory.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.SearchHistoryItemViewBinding
import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.ui.searchHistory.viewmodel.SearchHistoryViewModel
import com.example.ipfinderr.ui.searchHistory.viewholders.IpResultViewHolder
import com.google.gson.Gson

class SearchHistoryAdapter(private val results: List<IpResult>, private val viewModel: SearchHistoryViewModel): RecyclerView.Adapter<IpResultViewHolder>() {
    private val handler = Handler(Looper.getMainLooper())
    private var isClickAllowed = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IpResultViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return IpResultViewHolder(SearchHistoryItemViewBinding.inflate(layoutInspector, parent, false))
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: IpResultViewHolder, position: Int) {
        holder.bind(results[position])
        holder.itemView.setOnClickListener{
            if(clickDebounce()){
                viewModel.writeToHistory(results[position])
                holder.itemView.findNavController().navigate(R.id.action_searchHistoryFragment_to_mainFragment, bundleOf(
                    IP_RESULT_KEY to Gson().toJson(results[position])))
            }
        }
    }
    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }
    companion object{
        const val IP_RESULT_KEY = "IP_RESULT_KEY"
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }
}