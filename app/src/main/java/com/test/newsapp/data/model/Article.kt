package com.test.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var articleId: Int = 0,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    //@ColumnInfo(name = "source")
    //val source: Source,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,

    @ColumnInfo(name = "bookmark")
    val isBookMarked: Boolean = false


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
