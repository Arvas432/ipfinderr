package com.example.ipfinderr.ui.searchHistory

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ipfinderr.databinding.SearchHistoryItemViewBinding
import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.ui.Main.MainActivity
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
                val navigateToPlayerActivity = Intent(holder.itemView.context, MainActivity::class.java)
                navigateToPlayerActivity.putExtra(IP_RESULT_KEY, Gson().toJson(results[position]))
                navigateToPlayerActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                Log.i("intent", results[position].ip)
                holder.itemView.context.startActivity(navigateToPlayerActivity)
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