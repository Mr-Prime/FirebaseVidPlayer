package com.omega.firebasevidplayer.ui.VideoPlayer

import com.omega.firebasevidplayer.model.response.FirebaseResponse

class VideoView {
    interface View {
        fun responeUserInfo(dataUser: FirebaseResponse)
    }
}