package com.mobyle.recyclerviewexoplayer.presentation.feed.model

import androidx.media3.exoplayer.ExoPlayer
import com.mobyle.recyclerviewexoplayer.domain.model.Post

data class FeedPartialBindingPayload(val post: Post, val exoPlayer: ExoPlayer?)