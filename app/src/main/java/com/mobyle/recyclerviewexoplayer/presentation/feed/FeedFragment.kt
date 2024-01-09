package com.mobyle.recyclerviewexoplayer.presentation.feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
                    Log.d("HelpMe", "Loading")
                }

                is FeedViewModel.FeedUiState.FeedSuccess -> {
                    postsAdapter.setNewData(state.postsList)
                }

                FeedViewModel.FeedUiState.FeedError -> {
                    Log.d("HelpMe", "Error")
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
                Log.d("HelpMe", it.toString())
            }

            attachSnapHelperWithListener(
                snapHelper = snapHelper,
                snapListener = snapListener
            )
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
        }
    }
}