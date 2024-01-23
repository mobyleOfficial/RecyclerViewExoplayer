package com.mobyle.recyclerviewexoplayer.data.remote.di

import com.mobyle.recyclerviewexoplayer.data.remote.feed.FeedApi
import com.mobyle.recyclerviewexoplayer.data.remote.feed.FeedRemoteDataSource
import com.mobyle.recyclerviewexoplayer.data.remote.feed.FeedRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun feedRemoteDataSource(api: FeedApi): FeedRemoteDataSource = FeedRemoteDataSourceImpl(api)

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): FeedApi = retrofit.create(FeedApi::class.java)
}