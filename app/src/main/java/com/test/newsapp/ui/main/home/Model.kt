package com.test.newsapp.ui.main.home

import com.test.newsapp.data.model.Article

class Model(var id: String, var type: Int, var text: String, var data: Article?) {

    companion object {
        const val HEADER_TEXT_TYPE = 0
        const val TOP_NEWS_TYPE = 1
        const val POPULAR_NEWS_TYPE = 2
    }

}

