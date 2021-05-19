package com.example.movieapplication.ui

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.example.movieapplication.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    init {
        lifecycleScope.launchWhenResumed {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}