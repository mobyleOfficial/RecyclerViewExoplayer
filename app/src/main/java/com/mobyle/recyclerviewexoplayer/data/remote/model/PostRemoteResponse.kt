package com.mobyle.recyclerviewexoplayer.data.remote.model

import com.google.gson.annotations.SerializedName

data class PostRemoteResponse(
    @SerializedName("data")
    val data: PostListingResponse,
)