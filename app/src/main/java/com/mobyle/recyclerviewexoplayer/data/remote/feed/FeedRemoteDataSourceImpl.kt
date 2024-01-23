package com.mobyle.recyclerviewexoplayer.data.remote.feed

import com.mobyle.recyclerviewexoplayer.data.remote.model.PostListingResponse
import javax.inject.Inject

class FeedRemoteDataSourceImpl @Inject constructor(private val api: FeedApi) :
    FeedRemoteDataSource {
    // If you want to try HLS videos use this
    private val hlsVideosUrl =
        listOf(
            "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8",
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",
            "https://cph-p2p-msl.akamaized.net/hls/live/2000341/test/master.m3u8",

        )

    override suspend fun getFeed(): PostListingResponse {

        api.getVideos(0).body()?.let {
            val mappedPosts = it.data.posts.map { post ->
                val newVideo = post.video//.copy(mediaUrl = hlsVideosUrl.random())
                post.copy(video = newVideo)
            }

            return it.data.copy(posts = mappedPosts)
        } ?: kotlin.run {
            throw Exception("ERROR")
        }
    }
}