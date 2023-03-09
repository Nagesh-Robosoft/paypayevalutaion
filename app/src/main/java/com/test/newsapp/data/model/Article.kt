package com.test.newsapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("articleId") @Expose var articleId: Int = 0,

    @SerializedName("author") @Expose val author: String,

    @SerializedName("content") @Expose val content: String,

    @SerializedName("description") @Expose val description: String,

    @SerializedName("publishedAt") @Expose val publishedAt: String,

    @SerializedName("source") @Expose val source: Source,

    @SerializedName("title") @Expose val title: String,

    @SerializedName("url") @Expose val url: String,

    @SerializedName("urlToImage") @Expose val urlToImage: String,

    @SerializedName("isBookMarked") @Expose val isBookMarked: Boolean = false


) {
    companion object {
        fun from(newsObject: News): List<Article> {
            val cities = mutableListOf<Article>()

            for (element in newsObject.article) {
                cities.add(element)
            }
            return cities
        }
    }
}

data class Source(
    @SerializedName("id") @Expose var id: String, @SerializedName("name") @Expose var name: String
)
