package com.omega.firebasevidplayer.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

 class HomeResponse(
 //   @SerializedName("id")
    val id: Int,
  //  @SerializedName("thumb")
    val thumb: String = "",
  //  @SerializedName("title")
    val title: String = "",
 //   @SerializedName("url")
    val url: String = "",
 //   @SerializedName("description")
    val description: String = ""
): Parcelable{
     companion object {
         @JvmField
         val CREATOR = object : Parcelable.Creator<HomeResponse> {
             override fun createFromParcel(parcel: Parcel) = HomeResponse(parcel)
             override fun newArray(size: Int) = arrayOfNulls<HomeResponse>(size)
         }
     }
     private   constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flag: Int) {
        parcel.writeInt(id)
        parcel.writeString(thumb)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(description)
    }

     override fun describeContents() = 0


}