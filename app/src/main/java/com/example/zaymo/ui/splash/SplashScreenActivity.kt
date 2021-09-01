package com.example.zaymo.ui.splash

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.zaymo.R
import com.example.zaymo.ui.zaymo.ZaymoActivity


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_splash_screen)
        super.onCreate(savedInstanceState)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setElevation(0f)
        }

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, ZaymoActivity::class.java))
            finish()
        }, 200)

    }
}