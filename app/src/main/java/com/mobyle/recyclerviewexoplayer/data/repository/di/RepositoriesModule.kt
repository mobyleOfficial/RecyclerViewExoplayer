package com.mobyle.recyclerviewexoplayer.data.repository.di

import com.mobyle.recyclerviewexoplayer.data.repository.feed.FeedRepositoryImpl
import com.mobyle.recyclerviewexoplayer.domain.repository.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun feedRepository(impl: FeedRepositoryImpl): FeedRepository
}