package com.mobyle.recyclerviewexoplayer.domain.usecases

import com.mobyle.recyclerviewexoplayer.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeed @Inject constructor(private val repository: FeedRepository) {
    suspend fun invoke() = repository.getFeed()
}