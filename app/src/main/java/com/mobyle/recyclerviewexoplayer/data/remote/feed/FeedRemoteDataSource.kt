package com.mobyle.recyclerviewexoplayer.data.remote.feed

import com.mobyle.recyclerviewexoplayer.data.remote.model.PostListingResponse

interface FeedRemoteDataSource {
    suspend fun getFeed(page: Int): PostListingResponse
}