package com.test.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.test.newsapp.utils.Utils
import com.test.pokemongo.R
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val requestOptions = RequestOptions()
        Glide.with(this).load(arguments?.getString(KEY_IMAGE_URL)).apply(requestOptions)
            .placeholder(Utils.randomDrawableColor)
            .transition(DrawableTransitionOptions.withCrossFade()).into(ivNewsImage)
        ivNewsHeader.text = arguments?.getString(KEY_HEADLINE)
        if (arguments?.getString(KEY_DESCRIPTION)?.trim()?.isNotEmpty() == true) {
            tvNewsDescription.text = arguments?.getString(KEY_DESCRIPTION)?.trim()
            tvNewsDescription.visibility = View.VISIBLE
        } else {
            tvNewsDescription.visibility = View.GONE
        }
    }

    companion object {
        const val KEY_HEADLINE = "key_headline"
        const val KEY_IMAGE_URL = "key_image_url"
        const val KEY_DESCRIPTION = "key_description"

        @JvmStatic
        fun newInstance(headline: String, imageUrl: String, description: String) =
            NewsDetailFragment().apply {
                val bundle = Bundle()
                bundle.putString(KEY_HEADLINE, headline)
                bundle.putString(KEY_IMAGE_URL, imageUrl)
                bundle.putString(KEY_DESCRIPTION, description)
                arguments = bundle
            }
    }
}
