package com.omega.firebasevidplayer.utility

import androidx.fragment.app.FragmentManager
import com.omega.firebasevidplayer.ui.VideosHome.HomeFragment


object FragmentFactory{

    fun getHomeFragment(supportFragmentManager: FragmentManager): HomeFragment {
        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.FRAGMENT_NAME)
        if (fragment == null) {
            fragment = HomeFragment()
        }
        return fragment as HomeFragment
    }

}