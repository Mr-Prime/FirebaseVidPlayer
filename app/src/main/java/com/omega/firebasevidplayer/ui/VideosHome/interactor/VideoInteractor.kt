package com.omega.firebasevidplayer.ui.VideosHome.interactor

import android.util.Log
import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by Mr.Prime on 29-12-2019.
 */
class VideoInteractor(private val listener: IVideoInteractor) :
    Callback<List<HomeResponse>> {
    private val Client: RetrofitClient
    val videos: Unit
        get() {
            Client.Service().videos.enqueue(this)
        }

    override fun onResponse(
        call: Call<List<HomeResponse>>,
        response: Response<List<HomeResponse>>
    ) {
        if (response.code() == 200) {
            Log.e("VI", "Inside 200")
            val List: MutableList<HomeResponse> =
                ArrayList()
            response.body()?.let { List.addAll(it) }
            listener.onNetworkSuccess(List)
        } else {
            listener.onNetworkFailure()
        }
    }

    override fun onFailure(
        call: Call<List<HomeResponse>>,
        t: Throwable
    ) {
        listener.onNetworkFailure()
    }

    init {
        Client = RetrofitClient()
    }
}