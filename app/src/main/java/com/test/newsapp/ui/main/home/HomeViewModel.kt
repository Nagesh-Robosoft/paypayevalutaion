package com.test.newsapp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.newsapp.data.model.Article
import com.test.newsapp.data.remote.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

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
                }, {
                    _newsFailedLiveData.value = true
                })
        )
    }

    fun isFirstPage() = pageNum <= 2



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



