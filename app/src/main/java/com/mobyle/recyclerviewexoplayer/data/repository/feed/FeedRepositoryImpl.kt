package com.mobyle.recyclerviewexoplayer.data.repository.feed

import com.mobyle.recyclerviewexoplayer.data.remote.feed.FeedRemoteDataSource
import com.mobyle.recyclerviewexoplayer.domain.model.Post
import com.mobyle.recyclerviewexoplayer.domain.repository.FeedRepository
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(private val remoteDataSource: FeedRemoteDataSource) :
    FeedRepository {
    override suspend fun getFeed(): List<Post> {
        val response = remoteDataSource.getFeed()

        return response.posts.map {
            val video = it.video
            Post(
                postId = it.postId,
                videoUrl = video.mediaUrl,
                thumbnailUrl = video.thumbnailUrl
            )
        }
    }
}