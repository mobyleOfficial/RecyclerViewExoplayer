package com.mobyle.recyclerviewexoplayer.data.remote.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("mediaUrl")
    val mediaUrl: String,
)