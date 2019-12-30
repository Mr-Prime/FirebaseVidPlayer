package com.omega.firebasevidplayer.utility

interface BasePresenter {
    fun showeLoding()
    fun hideLoding()
    fun onError(message: String)

}