package com.omega.firebasevidplayer.ui.VideosHome

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.omega.firebasevidplayer.R
import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.ui.VideoPlayer.LoadInterface
import com.omega.firebasevidplayer.utility.FragmentFactory



class HomeActivity : AppCompatActivity(),LoadInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //   setSupportActionBar(toolbar)
        //   supportActionBar!!.setDisplayShowTitleEnabled(false)
        showHomeFragment()
    }

    private fun showHomeFragment() {
        var fragment: HomeFragment = HomeFragment.newInstance(this)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                fragment
            )
        // fragmentTransaction.addToBackStack(fragment)
        fragmentTransaction.commit()
    }

    override fun OnLoad(videos: List<HomeResponse>) {

    }
}