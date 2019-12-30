package com.omega.firebasevidplayer.ui.VideosHome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.omega.firebasevidplayer.R
import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.ui.VideoPlayer.LoadInterface


class HomeActivity : AppCompatActivity(),LoadInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //   setSupportActionBar(toolbar)
        //   supportActionBar!!.setDisplayShowTitleEnabled(false)
        showHomeFragment()
    }

    private fun showHomeFragment() {
        var fragment: HomeFragment = HomeFragment.newInstance(this, 0)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                fragment
            )
        // fragmentTransaction.addToBackStack(fragment)
        fragmentTransaction.commit()
    }

    override fun OnLoad(
        videos: MutableList<HomeResponse>,
        posi: Int
    ) {

    }
}