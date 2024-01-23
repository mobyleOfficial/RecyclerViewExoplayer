package com.mobyle.recyclerviewexoplayer.presentation.feed.adapter

import android.view.ViewGroup
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.mobyle.recyclerviewexoplayer.databinding.ItemPostBinding
import com.mobyle.recyclerviewexoplayer.domain.model.Post
import com.mobyle.recyclerviewexoplayer.presentation.commons.viewBinding
import com.mobyle.recyclerviewexoplayer.presentation.feed.model.FeedPartialBindingPayload

class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: MutableList<FeedPartialBindingPayload> = mutableListOf()
    private var shouldForcePartialBind = true

    fun setNewData(newItems: List<Post>) {
        items.addAll(newItems.map { FeedPartialBindingPayload(post = it, exoPlayer = null) })
        notifyDataSetChanged()
    }

    fun attachPlayerAtPosition(position: Int, exoPlayer: ExoPlayer) {
        if (items.isNotEmpty()) {
            val currentPost = items[position]
            items[position] =
                items[position].copy(
                    post = currentPost.post,
                    exoPlayer = exoPlayer
                )

            notifyItemChanged(position, items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FeedViewHolder(parent.viewBinding(ItemPostBinding::inflate))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedViewHolder) {
            holder.bind(items[position].post)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        when {
            payloads.isEmpty() -> {
                if (shouldForcePartialBind) {
                    val currentItem = items[position]

                    if (holder is FeedViewHolder) {
                        holder.bind(currentItem.post)
                        holder.playVideo(currentItem.exoPlayer)
                        shouldForcePartialBind = false
                    }

                } else {
                    onBindViewHolder(holder, position)
                }
            }

            else -> {
                (payloads[0] as? FeedPartialBindingPayload)?.let {
                    (holder as FeedViewHolder).playVideo(it.exoPlayer)
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)

        if (holder is FeedViewHolder) {
            holder.pausePlayer()
            holder.detachPlayer()
            holder.showThumbnail()
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)

        if (holder is FeedViewHolder) {
            holder.detachPlayer()
            holder.hidePlayer()
            holder.showThumbnail()
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)

        if (holder is FeedViewHolder) {
            holder.detachPlayer()
            holder.hidePlayer()
        }
    }
}
