package com.islamzada.cryptotrackerapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.islamzada.cryptotrackerapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val backgroundImg : ImageView = findViewById(R.id.ltAnimation)
        val slideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide)
        backgroundImg.startAnimation(slideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000)
    }
}