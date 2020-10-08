package com.onoh.finalgithubapps.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.onoh.finalgithubapps.R

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        val  splashTimeout:Long = 3000 // 1 sec

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, splashTimeout)
    }

}
