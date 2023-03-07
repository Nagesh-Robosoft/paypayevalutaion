package com.test.newsapp.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.newsapp.di.ActivityScope
import com.test.newsapp.data.model.Article
import com.test.newsapp.data.remote.NewsRepository
import com.test.newsapp.database.DatabaseService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor(
    private val databaseService: DatabaseService,
    private val newsRepository: NewsRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private var pageNum = 1
    var isPaginationDone = false
    private val _newsLiveData = MutableLiveData<List<Article>>()
    val newsLiveData: LiveData<List<Article>>
        get() = _newsLiveData


    private val _newsFailedLiveData = MutableLiveData<Boolean>()
    val newsFailedLiveData: LiveData<Boolean>
        get() = _newsFailedLiveData


    fun getNews() {
        compositeDisposable.add(
            newsRepository.getNews(pageNum)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.size < Constant.PAGE_SIZE) isPaginationDone = true else pageNum += 1
                    _newsLiveData.value = it
                    insertBookmarkedArticle(it)
                }, {
                    _newsFailedLiveData.value = true
                })
        )
    }

    fun isFirstPage() = pageNum <= 2

    private fun insertBookmarkedArticle(list: List<Article>) {
        list.forEach {
            insertItem(it)
        }
    }

    private fun insertItem(article: Article) {
        compositeDisposable.add(
            databaseService.getArticleDao().insertNewsArticle(article)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.e("value inserted", article.title)
                }, {
                    Log.e("value failed to insert", article.title)
                })
        )
    }

    private fun bookMarkArticle(article: Article, isfav: Boolean) {
        compositeDisposable.add(
            databaseService.getArticleDao().saveOrRemoveBookmarkArticle(isfav, article.articleId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it is Int) {
                        Log.e("value updated success ", article.title)
                    } else
                        Log.e("value updated failed ", article.title)

                }, {
                    Log.e("value failed to insert", article.title)
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        pageNum = 1
        isPaginationDone = false
        compositeDisposable.clear()
    }

    object Constant {
        const val PAGE_SIZE = 10
    }
}



