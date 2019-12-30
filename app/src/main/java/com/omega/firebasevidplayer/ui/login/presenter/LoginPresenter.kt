package com.omega.firebasevidplayer.ui.login.presenter

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.omega.firebasevidplayer.model.response.FirebaseResponse
import com.omega.firebasevidplayer.ui.login.LoginView


class LoginPresenter(private val view: LoginView.View) {

    private var firebaseAuth: FirebaseAuth? = null
    init {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun setLogin(data: Intent) {
        view.showeLoding()

        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let { firebaseAuthWithGoogle(it) }

        } catch (e: ApiException) {
            view.onError(e.toString())
           // Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
        }

    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener {
            view.hideLoding()
            if (it.isSuccessful) {
                view.isSuccess()
            } else {
                view.onError(it.exception.toString())
            }
        }
    }



}