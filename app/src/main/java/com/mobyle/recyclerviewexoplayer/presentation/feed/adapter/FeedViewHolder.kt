package com.mobyle.recyclerviewexoplayer.presentation.feed.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mobyle.recyclerviewexoplayer.databinding.ItemPostBinding
import com.mobyle.recyclerviewexoplayer.domain.model.Post
import kotlinx.coroutines.delay

class FeedViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            videoPlayer.player = null
            ivThumbnail.visibility = View.VISIBLE
            ivThumbnail.load(post.thumbnailUrl)

            videoPlayer.setOnTouchListener(object : View.OnTouchListener {
                val gestureDetector: GestureDetector = GestureDetector(
                    itemView.context,
                    @UnstableApi object : GestureDetector.SimpleOnGestureListener() {

                        override fun onSingleTapUp(e: MotionEvent): Boolean {
                            super.onSingleTapUp(e)
                            videoPlayer.player?.let { player ->
                                when (player.isPlaying) {
                                    true -> {
                                        player.pause()
                                        videoPlayer.showController()
                                    }

                                    else -> {
                                        player.play()
                                        videoPlayer.hideController()
                                    }
                                }
                            }
                            return true
                        }
                    }
                )

                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(view: View?, event: MotionEvent): Boolean {
                    gestureDetector.onTouchEvent(event)
                    return true
                }
            })
        }
    }

    fun playVideo(receivedPlayer: ExoPlayer?) {
        receivedPlayer?.let {
            with(binding.videoPlayer) {
                post {
                    visibility = View.VISIBLE
                    player = it
                    player?.addListener(
                        @UnstableApi object : Player.Listener {
                            override fun onIsPlayingChanged(isPlaying: Boolean) {

                            }

                            override fun onRenderedFirstFrame() {
                                super.onRenderedFirstFrame()

                            }

                            override fun onPlaybackStateChanged(playbackState: Int) {
                                when (playbackState) {
                                    Player.STATE_READY -> {
                                        // do something
                                        Log.d("HelpMe", "Starting")
                                        binding.ivThumbnail.visibility = View.GONE
                                    }

                                    Player.STATE_BUFFERING -> {

                                    }

                                    else -> {} // no-op
                                }
                            }

                            override fun onPlayerError(error: PlaybackException) {
                                Log.d("HelpMe", error.toString())
                            }
                        }
                    )
                }
            }
        } ?: kotlin.run {
            detachPlayer()
        }
    }

    fun detachPlayer() {
        with(binding) {
            videoPlayer.player = null
            videoPlayer.player?.clearMediaItems()
            videoPlayer.player?.pause()
        }
    }

    fun showThumbnail() {
        binding.ivThumbnail.visibility = View.VISIBLE
    }

    fun hidePlayer() {
        binding.videoPlayer.visibility = View.INVISIBLE
    }

    fun pausePlayer() {
        binding.videoPlayer.player?.pause()
    }

}