package com.mobyle.recyclerviewexoplayer.data.remote.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("postId")
    val postId: String,
    @SerializedName("submission")
    val video: VideoResponse
)