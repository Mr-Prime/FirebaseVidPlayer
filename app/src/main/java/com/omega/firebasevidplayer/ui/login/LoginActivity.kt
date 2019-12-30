package com.omega.firebasevidplayer.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.omega.firebasevidplayer.R
import com.omega.firebasevidplayer.ui.VideosHome.HomeActivity
import com.omega.firebasevidplayer.ui.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView.View {

    private lateinit var presenter: LoginPresenter
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

// Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        hideLoding()





     //   StatusbarManager.darkenStatusBar(this)
        presenter = LoginPresenter(this)
        onEvent()
    }

    private fun onEvent() {
        btnSignIn.setOnClickListener {
            showeLoding()
            // Configure Google Sign In
            mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
             mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            if (data != null) {
                presenter.setLogin(data)
            }


        }
    }

    override fun isSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
       // ActivityTransition.GoLeft(this)
        finish()
    }

    override fun showeLoding() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoding() {
        pb.visibility = View.GONE
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        isSuccess()
    }

}
