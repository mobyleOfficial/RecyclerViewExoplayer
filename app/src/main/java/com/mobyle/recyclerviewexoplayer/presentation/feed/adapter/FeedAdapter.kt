package com.mobyle.recyclerviewexoplayer.presentation.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobyle.recyclerviewexoplayer.databinding.ItemPostBinding
import com.mobyle.recyclerviewexoplayer.domain.model.Post
import com.mobyle.recyclerviewexoplayer.presentation.commons.viewBinding

class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: MutableList<Post> = mutableListOf()

    fun setNewData(newItems: List<Post>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FeedViewHolder(parent.viewBinding(ItemPostBinding::inflate))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedViewHolder) {
            holder.bind(items[position])
        }
    }
}
