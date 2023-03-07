package com.test.newsapp.data.remote

import com.test.newsapp.data.model.News
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface to call API
 */
interface ApiNewsInterface{

    companion object {
        private const val API_KEY :  String = "4356fa118fbc480d857527402b6834e2"
    }

    @GET("top-headlines")
    fun trendingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNum: Int,
        @Query("pageSize")
        pageSize: Int? = null,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Single<News>


    /**
     * News search
     */
    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q")
        query :String = "bitcoin",
        @Query("page")
        pageNum: Int,
        @Query("apiKey")
        api_key: String = API_KEY
    ): Single<News>

}
