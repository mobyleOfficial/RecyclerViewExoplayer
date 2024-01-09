package com.mobyle.recyclerviewexoplayer.presentation.feed.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mobyle.recyclerviewexoplayer.databinding.ItemPostBinding
import com.mobyle.recyclerviewexoplayer.domain.model.Post

class FeedViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            ivThumbnail.load(post.thumbnailUrl)
            tvId.text = post.postId
        }
    }

    fun setPositionAsId(position: Int) {
        binding.tvId.text = position.toString()
    }
}