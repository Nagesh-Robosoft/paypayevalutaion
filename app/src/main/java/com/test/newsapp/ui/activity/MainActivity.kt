package com.test.newsapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.newsapp.ui.fragments.HomeFragment
import com.test.pokemongo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        replaceFragment(HomeFragment())

    }

    private fun replaceFragment(fragment: Fragment){
       supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun replaceFragmentAndAddToBackStack(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(fragment::class.simpleName)
            .commit()
    }
}

