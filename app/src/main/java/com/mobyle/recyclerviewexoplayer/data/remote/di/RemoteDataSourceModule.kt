package com.mobyle.recyclerviewexoplayer.data.remote.di

import com.mobyle.recyclerviewexoplayer.data.remote.feed.FeedRemoteDataSource
import com.mobyle.recyclerviewexoplayer.data.remote.feed.FeedRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun feedRemoteDataSource(impl: FeedRemoteDataSourceImpl): FeedRemoteDataSource
}