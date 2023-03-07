package com.test.newsapp.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.newsapp.MyApplication
import com.test.newsapp.di.component.DaggerActivityComponent
import com.test.newsapp.di.module.ActivityModule
import com.test.newsapp.ui.detail.NewsDetailActivity
import com.test.pokemongo.R
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment(), MultiViewTypeAdapter.ListItemClickListener {
    @Inject
    lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MultiViewTypeAdapter
    private var list: ArrayList<Model> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDependencies()
        setUpRecyclerView()
        readLiveData()
        viewModel.getNews()
    }
    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((activity?.application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(activity!!))
            .build()
            .inject(this)
    }
    private fun readLiveData() {
        viewModel.newsFailedLiveData.observe(this, androidx.lifecycle.Observer {
            Toast.makeText(activity, "failed", Toast.LENGTH_LONG).show()
        })
        viewModel.newsLiveData.observe(this, androidx.lifecycle.Observer {
            if (viewModel.isFirstPage()) {
                val tempList: ArrayList<Model> = arrayListOf()
                val topNewsModel = Model(id = "Top News", text = "Top News", type = Model.HEADER_TEXT_TYPE, data = null)
                tempList.add(topNewsModel)
                val topNews =
                    Model(id = it.first().articleId.toString(), data = it.first(), type = Model.TOP_NEWS_TYPE, text = "")
                if (it.isNotEmpty()) tempList.add(topNews)
                val popularNewsModel =
                    Model(id = "Popular News", text = "Popular News", type = Model.HEADER_TEXT_TYPE, data = null)
                tempList.add(popularNewsModel)
                it.subList(1, it.size - 1).forEach {
                    tempList.add(Model(id = it.articleId.toString(), data = it, type = Model.POPULAR_NEWS_TYPE, text = ""))
                }
                list.clear()
                list.addAll(tempList)
            } else {
                it.forEach {
                    list.add(Model(id = it.articleId.toString(), data = it, type = Model.POPULAR_NEWS_TYPE, text = ""))
                }
            }
            adapter.updateData(list)
            adapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecyclerView() {
        adapter = MultiViewTypeAdapter(this)
        adapter.updateData(ArrayList())
        homeNewsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        homeNewsRecyclerView.adapter = adapter
        homeNewsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((homeNewsRecyclerView?.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == list.size - 1) {
                    if (!viewModel.isPaginationDone) {
                        viewModel.getNews()
                    }
                }
            }
        })
    }

    override fun onItemClick(item: Model, position: Int) {

        val intent = Intent(activity, NewsDetailActivity::class.java)
        intent.putExtra("url", item.data?.url.orEmpty())
        intent.putExtra("title", item.data?.url.orEmpty())
        intent.putExtra("date", item.data?.url.orEmpty())
        intent.putExtra("source", item.data?.url.orEmpty())
        intent.putExtra("author", item.data?.url.orEmpty())
        startActivity(intent)
    }
}
