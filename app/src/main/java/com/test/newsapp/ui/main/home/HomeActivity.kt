package com.test.newsapp.ui.main.home

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.test.newsapp.ui.base.BaseActivity
import com.test.pokemongo.R

class HomeActivity : BaseActivity() {
    override fun provideLayout() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.navHostContainer, HomeFragment()).commit()
        }

    }
}
