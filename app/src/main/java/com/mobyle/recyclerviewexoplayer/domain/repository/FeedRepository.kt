package com.mobyle.recyclerviewexoplayer.domain.repository

import com.mobyle.recyclerviewexoplayer.domain.model.Post

interface FeedRepository {
    suspend fun getFeed(): List<Post>
}