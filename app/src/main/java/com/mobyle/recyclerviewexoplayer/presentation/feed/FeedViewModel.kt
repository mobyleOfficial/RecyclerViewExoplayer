package com.mobyle.recyclerviewexoplayer.presentation.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobyle.recyclerviewexoplayer.domain.model.Post
import com.mobyle.recyclerviewexoplayer.domain.usecases.GetFeed
import com.mobyle.recyclerviewexoplayer.infra.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val getFeedUseCase: GetFeed) : BaseViewModel() {
    private val _uiState =
        MutableLiveData<FeedUiState>()
    val uiState: LiveData<FeedUiState> = _uiState

    private var page = 0
    val postsList = mutableListOf<Post>()

    fun getFeed() = launch {
        _uiState.postValue(FeedUiState.FeedLoading)

        try {
            val result = getFeedUseCase.invoke(page)
            postsList.clear()
            postsList.addAll(result.posts)
            _uiState.postValue(FeedUiState.FeedSuccess(postsList))
        } catch (_: Exception) {
            _uiState.postValue(FeedUiState.FeedError)
        }
    }

    sealed class FeedUiState {
        object FeedLoading : FeedUiState()
        data class FeedSuccess(val postsList: List<Post>) : FeedUiState()
        object FeedError : FeedUiState()
    }
}