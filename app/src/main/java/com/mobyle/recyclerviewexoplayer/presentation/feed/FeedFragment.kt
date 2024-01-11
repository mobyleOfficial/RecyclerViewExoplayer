package com.mobyle.recyclerviewexoplayer.presentation.feed

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mobyle.recyclerviewexoplayer.R
import com.mobyle.recyclerviewexoplayer.databinding.ActivityMainBinding
import com.mobyle.recyclerviewexoplayer.databinding.FragmentFeedBinding
import com.mobyle.recyclerviewexoplayer.presentation.commons.SnapOnScrollListener
import com.mobyle.recyclerviewexoplayer.presentation.commons.attachSnapHelperWithListener
import com.mobyle.recyclerviewexoplayer.presentation.feed.adapter.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private val viewModel: FeedViewModel by viewModels()
    private val postsAdapter = FeedAdapter()
    private lateinit var snapListener: SnapOnScrollListener
    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(requireContext())
            .build()
            .apply {
                repeatMode = Player.REPEAT_MODE_ALL
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getFeed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                FeedViewModel.FeedUiState.FeedLoading -> {
                    // Show loading
                }

                is FeedViewModel.FeedUiState.FeedSuccess -> {
                    val postList = state.postsList
                    postsAdapter.setNewData(state.postsList)

                    if (postList.isNotEmpty()) {
                        prepareToPlayVideo(0)
                        playVideo(0)
                    }
                }

                FeedViewModel.FeedUiState.FeedError -> {
                    // Show some error
                }
            }
        }
    }

    private fun initializeRecyclerView() {
        with(binding.rvFeed) {
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)

            snapListener = SnapOnScrollListener(
                snapHelper,
                SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE
            ) {
                prepareToPlayVideo(it)
                playVideo(it)
            }

            attachSnapHelperWithListener(
                snapHelper = snapHelper,
                snapListener = snapListener
            )
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
        }
    }

    @OptIn(UnstableApi::class)
    private fun prepareToPlayVideo(position: Int) {
        viewModel.postsList.getOrNull(position)?.let { post ->
            val mediaItem = MediaItem.Builder()
                .setMimeType(MimeTypes.APPLICATION_M3U8)
                .setUri(post.videoUrl)
                .build()
            val dataSourceFactory = DefaultHttpDataSource.Factory()
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaItem)
            //HLS
            //val mediaSource = HlsMediaSource.Factory(dataSourceFactory)
            //createMediaSource(mediaItem)

            player.setMediaItem(mediaItem)

            Handler(Looper.getMainLooper()).post {
                player.setMediaSource(mediaSource)
                player.prepare()
            }
        }
    }

    private fun playVideo(position: Int) {
        postsAdapter.attachPlayerAtPosition(position, player)
        player.playWhenReady = true
    }
}