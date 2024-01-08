package com.mobyle.recyclerviewexoplayer.data.remote.model

import com.google.gson.annotations.SerializedName

data class PostListingResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("posts")
    val posts: List<PostResponse>
)