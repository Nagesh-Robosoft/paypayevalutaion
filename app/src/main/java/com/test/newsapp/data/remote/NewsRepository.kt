package com.test.newsapp.data.remote

import com.test.newsapp.data.model.Article
import com.test.newsapp.di.module.NetworkModule
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * This class makes the network calls to get list of article.
 */
class NewsRepository(private val networkModule: NetworkModule) {

    fun getNews(pageNum: Int): Single<List<Article>> {
        return networkModule.provideRetrofitService().trendingNews("in", pageNum, pageSize = 10)
            .subscribeOn(Schedulers.io()).map {
                Article.from(it)
            }
    }
}

