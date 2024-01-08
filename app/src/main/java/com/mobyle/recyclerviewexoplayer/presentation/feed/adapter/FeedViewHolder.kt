package com.mobyle.recyclerviewexoplayer.presentation.feed.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mobyle.recyclerviewexoplayer.databinding.ItemPostBinding
import com.mobyle.recyclerviewexoplayer.domain.model.Post

class FeedViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.tvId.text = post.postId
    }
}