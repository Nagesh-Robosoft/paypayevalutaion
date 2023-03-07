package com.test.newsapp.ui.main


import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.newsapp.MyApplication
import com.test.newsapp.data.model.Article
import com.test.newsapp.database.ArticleDao
import com.test.newsapp.database.DatabaseService
import com.test.newsapp.di.component.DaggerActivityComponent
import com.test.newsapp.di.module.ActivityModule
import com.test.newsapp.ui.adapter.Adapter
import com.test.newsapp.ui.base.BaseActivity
import com.test.newsapp.viewmodels.NewsViewModel
import com.test.pokemongo.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class NewsHomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: NewsViewModel

    private lateinit var adapter: Adapter
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var articles: List<Article> = ArrayList()

    @Inject
    lateinit var databaseService: DatabaseService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()
       // viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        layoutManager = LinearLayoutManager(this@NewsHomeActivity)
        recyclerView.layoutManager = layoutManager
         recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        observeLiveData()
    }

    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    override fun provideLayout() = R.layout.activity_main

    private fun observeLiveData() {

        viewModel.newsFailedLiveData.observe(this , androidx.lifecycle.Observer {
            Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
        })

        viewModel.newsLiveData.observe(this , androidx.lifecycle.Observer {
            articles = it!!
            adapter = Adapter(it!!, this@NewsHomeActivity)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
            it.forEach {article->
                val c = databaseService.getArticleDao().insertNewsArticle(article)
                Toast.makeText(this, "inserted = "+c, Toast.LENGTH_LONG).show()
            }

        })

    }


}
