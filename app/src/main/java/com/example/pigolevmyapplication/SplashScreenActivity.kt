package com.example.pigolevmyapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity



import android.os.*
import android.view.WindowManager

//Animation by Anastasiya Remeslova taken from https://lottiefiles.com

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // Start the main activity after a delay
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}