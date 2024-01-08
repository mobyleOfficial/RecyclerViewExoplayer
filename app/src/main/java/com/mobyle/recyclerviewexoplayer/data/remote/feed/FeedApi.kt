package com.mobyle.recyclerviewexoplayer.data.remote.feed

import com.mobyle.recyclerviewexoplayer.data.remote.model.PostRemoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {
    @GET("/videos")
    suspend fun getVideos(@Query("page")pageNumber: Int): Response<PostRemoteResponse>
}