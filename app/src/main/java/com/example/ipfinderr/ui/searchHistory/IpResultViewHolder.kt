package com.example.ipfinderr.ui.searchHistory

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.SearchHistoryItemViewBinding
import com.example.ipfinderr.domain.IpResult

class IpResultViewHolder(private val binding: SearchHistoryItemViewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: IpResult){
        binding.ipTv.text = model.ip
        Glide.with(itemView)
            .load(model.imageUrl)
            .placeholder(R.drawable.x)
            .fitCenter()
            .into(binding.flag)
    }
}
