package com.omega.firebasevidplayer.ui.VideoPlayer

import com.omega.firebasevidplayer.model.response.HomeResponse

/**
 * Created by Mr.Prime on 29-12-2019.
 */
interface LoadInterface {
    fun OnLoad(
        videos: MutableList<HomeResponse>,
        posi: Int
    )

}