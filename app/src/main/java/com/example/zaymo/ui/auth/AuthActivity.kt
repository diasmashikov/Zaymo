package com.example.zaymo.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.ActionBar
import com.example.zaymo.R
import com.example.zaymo.ui.zaymo.ZaymoActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)



        authButton.setOnClickListener {
            val intent = Intent(this, ZaymoActivity::class.java)
            startActivity(intent)
        }
    }
}