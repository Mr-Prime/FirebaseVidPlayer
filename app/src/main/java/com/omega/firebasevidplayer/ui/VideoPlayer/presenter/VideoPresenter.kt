package com.omega.firebasevidplayer.ui.VideoPlayer.presenter

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.omega.firebasevidplayer.model.response.FirebaseResponse
import com.omega.firebasevidplayer.ui.VideoPlayer.VideoPlayerActivity
import com.omega.firebasevidplayer.ui.VideoPlayer.VideoView


class VideoPresenter(private val view: VideoView.View) {

    private var firebaseAuth: FirebaseAuth? = null
    var reference: DatabaseReference? = null
    init {
        firebaseAuth = FirebaseAuth.getInstance()
    }
    internal var data: FirebaseResponse? = null
     fun StoreData(id: String,seek: Long) {

        val user1 = FirebaseResponse(id, seek)

         reference=FirebaseDatabase.getInstance().getReference("SeekData")
         reference?.child(id)?.setValue(user1);

    }
    fun getVideoInfo(id: String){
        reference = FirebaseDatabase.getInstance().getReference("SeekData").child(id)
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
             //  var id :String = p0.key?.get(0).toString()
              //    Log.e("reference","values "+id)
              // var seek :String = p0.key?.g

                data= p0.getValue(FirebaseResponse::class.java)
                if(p0.exists())
                view.responeUserInfo(data!!)
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }

}