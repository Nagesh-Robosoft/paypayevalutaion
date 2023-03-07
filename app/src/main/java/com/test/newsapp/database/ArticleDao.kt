package com.test.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.newsapp.data.model.Article
import io.reactivex.Single

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsArticle(article: Article) : Single<Long>

    @Query("SELECT * FROM article")
    fun getNewsArticles(): LiveData<List<Article>>

    @Delete
    fun deleteNewsArticle(article: Article)

    @Query("UPDATE article SET bookmark = :isArticleBookMarked WHERE articleId = :articleId")
    fun saveOrRemoveBookmarkArticle(isArticleBookMarked: Boolean, articleId : Int) : Single<Int>

    @Query("SELECT * FROM article WHERE bookmark = 1 ")
    fun getBookMarkedArticles(): LiveData<List<Article>>

}
