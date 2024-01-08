package com.mobyle.recyclerviewexoplayer.domain.repository

import com.mobyle.recyclerviewexoplayer.domain.model.PostListing

interface FeedRepository {
    suspend fun getFeed(page: Int): PostListing
}