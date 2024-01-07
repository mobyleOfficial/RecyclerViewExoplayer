package com.mobyle.recyclerviewexoplayer.data.repository.feed

import com.mobyle.recyclerviewexoplayer.data.remote.feed.FeedRemoteDataSource
import com.mobyle.recyclerviewexoplayer.domain.repository.FeedRepository
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(remoteDataSource: FeedRemoteDataSource) : FeedRepository {
}