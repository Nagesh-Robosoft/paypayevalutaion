package com.test.pokemongo.ui.base

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(provideLayout() ,container ,false )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpvView(view)
    }


    @LayoutRes
    abstract protected  fun provideLayout() : Int

    protected abstract fun  setUpvView(view: View)

}