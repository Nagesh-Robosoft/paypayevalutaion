package com.test.pokemongo.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.test.newsapp.ui.base.BaseActivity
import com.test.newsapp.ui.main.home.HomeActivity
import com.test.pokemongo.R

/**
 * This class is the starting class of app.
 */

class SplashActivity : BaseActivity() {

    override fun provideLayout(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        Handler().postDelayed(
            {
                val i = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(i)
                finish()


            }, 5000
        )
    }
}
