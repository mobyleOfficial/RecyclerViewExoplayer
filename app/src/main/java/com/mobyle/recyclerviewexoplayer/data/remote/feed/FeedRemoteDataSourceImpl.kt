package com.mobyle.recyclerviewexoplayer.data.remote.feed

import com.mobyle.recyclerviewexoplayer.data.remote.model.PostListingResponse
import com.mobyle.recyclerviewexoplayer.data.remote.model.PostRemoteResponse
import retrofit2.Retrofit
import javax.inject.Inject

class FeedRemoteDataSourceImpl @Inject constructor(private val api: FeedApi) :
    FeedRemoteDataSource {
    private val hlsVideosUrl =
        listOf(
            "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8",
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",
            "https://cph-p2p-msl.akamaized.net/hls/live/2000341/test/master.m3u8",

        )

    override suspend fun getFeed(page: Int): PostListingResponse {

        api.getVideos(page).body()?.let {
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