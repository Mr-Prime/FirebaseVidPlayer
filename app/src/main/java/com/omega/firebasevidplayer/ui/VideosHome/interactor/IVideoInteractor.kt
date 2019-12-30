package com.omega.firebasevidplayer.ui.VideosHome.interactor

import com.omega.firebasevidplayer.model.response.HomeResponse

/**
 * Created by Mr.Prime on 29-12-2019.
 */
interface IVideoInteractor {
    fun onNetworkSuccess(videos: MutableList<HomeResponse>)
    fun onNetworkFailure()
}