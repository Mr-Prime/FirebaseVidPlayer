package com.omega.firebasevidplayer.ui.VideosHome

import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.utility.BasePresenter


class HomeView {
    interface View : BasePresenter {
        fun onVideosLoadedSuccess(videos: MutableList<HomeResponse>)
        fun onnVideosLoadedError()
    }
}