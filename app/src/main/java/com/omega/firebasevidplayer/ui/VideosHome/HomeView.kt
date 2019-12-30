package com.omega.firebasevidplayer.ui.VideosHome

import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.utility.BasePresenter


class HomeView {
    interface View : BasePresenter {
        fun onVideosLoadedSuccess(videos: List<HomeResponse>)
        fun onnVideosLoadedError()
    }
}