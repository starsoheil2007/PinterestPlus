package com.soulvana.pinterestplus.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import android.view.animation.AnimationUtils
import com.soulvana.pinterestplus.R
import com.soulvana.pinterestplus.view_models.SplashActivityViewModel
import com.soulvana.pinterestplus.views.factories.MainViewModelFactory


class SplashActivity : AppCompatActivity() {


    private lateinit var binding: com.soulvana.pinterestplus.databinding.ActivitySplashBinding
    private lateinit var viewModel: SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Handle full screen view
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // binding view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(this)).get(SplashActivityViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        startTimer()
        // Animation of logo
        val animZoomOut = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)
        binding.logo.startAnimation(animZoomOut)
    }

    /**
     * Start Timer to show logo and go to main page
     *
     */
    fun startTimer() {
        val handler = Handler()
        handler.postDelayed({
            goToMainPage()
        }, 2000)
    }

    /**
     * go to main page an finish this activity
     *
     */
    private fun goToMainPage() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
