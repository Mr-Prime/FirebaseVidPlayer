package com.omega.firebasevidplayer.ui.VideosHome.presenter


import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.ui.VideosHome.HomeView
import com.omega.firebasevidplayer.ui.VideosHome.interactor.IVideoInteractor
import com.omega.firebasevidplayer.ui.VideosHome.interactor.VideoInteractor

class homePresenter(private val view: HomeView.View) :
    IVideoInteractor {

    private var interactor: VideoInteractor? = null

    fun getListImage(){
        interactor = VideoInteractor(this)
        interactor!!.videos
    }

    override fun onNetworkSuccess(videos: List<HomeResponse>) {
        view.onVideosLoadedSuccess(videos)
    }

    override fun onNetworkFailure() {

    }
}