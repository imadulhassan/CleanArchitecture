package com.sample.ui.splash


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.databinding.ActivitySplashBinding
import com.sample.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Splash : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            moveToNextActivity()
        }
    }

    private fun moveToNextActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}