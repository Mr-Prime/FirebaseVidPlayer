package com.omega.firebasevidplayer.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
@IgnoreExtraProperties
 class FirebaseResponse(

    val id: String,

    val seekto: Long = 0

) {
    constructor() : this("", 0) {
    }
}