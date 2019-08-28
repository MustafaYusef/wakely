package com.mustafayusef.wakely.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils

import com.mustafayusef.wakely.MainActivity
import com.mustafayusef.wakely.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class Lottie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
        Handler().postDelayed({



                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

        },2000)
    }
}
