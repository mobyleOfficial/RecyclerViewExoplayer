package com.mobyle.recyclerviewexoplayer.data.remote.feed

import com.mobyle.recyclerviewexoplayer.data.remote.model.PostListingResponse
import com.mobyle.recyclerviewexoplayer.data.remote.model.PostRemoteResponse
import retrofit2.Retrofit
import javax.inject.Inject

class FeedRemoteDataSourceImpl @Inject constructor(private val api: FeedApi) :
    FeedRemoteDataSource {
    private val hlsVideosUrl =
        listOf(
            "http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8",
            "http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8",
            "http://cdn-fms.rbs.com.br/vod/hls_sample1_manifest.m3u8",
            "http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8",
            "http://walterebert.com/playground/video/hls/sintel-trailer.m3u8",
            "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8",
            "https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8",
            "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8",
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",
            "http://d3rlna7iyyu8wu.cloudfront.net/skip_armstrong/skip_armstrong_stereo_subs.m3u8",
            "http://d3rlna7iyyu8wu.cloudfront.net/skip_armstrong/skip_armstrong_multichannel_subs.m3u8",
            "https://res.cloudinary.com/dannykeane/video/upload/sp_full_hd/q_80:qmax_90,ac_none/v1/dk-memoji-dark.m3u8",
            "https://d1gnaphp93fop2.cloudfront.net/videos/multiresolution/rendition_new10.m3u8",
            "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
            "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
            "https://diceyk6a7voy4.cloudfront.net/e78752a1-2e83-43fa-85ae-3d508be29366/hls/fitfest-sample-1_Ott_Hls_Ts_Avc_Aac_16x9_1280x720p_30Hz_6.0Mbps_qvbr.m3u8\n",
            "https://assets.afcdn.com/video49/20210722/v_645516.m3u8",
        )

    override suspend fun getFeed(page: Int): PostListingResponse {

        api.getVideos(page).body()?.let {
            val mappedPosts = it.data.posts.map { post ->
                val newVideo = post.video.copy(mediaUrl = hlsVideosUrl.random())
                post.copy(video = newVideo)
            }

            return it.data.copy(posts = mappedPosts)
        } ?: kotlin.run {
            throw Exception("ERROR")
        }
    }
}