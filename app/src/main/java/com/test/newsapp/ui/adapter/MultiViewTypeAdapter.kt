package com.test.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.newsapp.ui.model.Model
import com.test.newsapp.utils.Utils
import com.test.pokemongo.databinding.ItemHeaderBinding
import com.test.pokemongo.databinding.ItemPopularNewsBinding
import com.test.pokemongo.databinding.ItemTopNewsBinding

class MultiViewTypeAdapter(private val listItemClickListener: ListItemClickListener) :
    ListAdapter<Model, RecyclerView.ViewHolder>(ListItemCallback()) {
    private lateinit var dataSet: ArrayList<Model>

    interface ListItemClickListener {
        fun onItemClick(item: Model, position: Int)
    }

    open class ListItemCallback : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class HeaderViewHolder(var binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    class TopNewsViewHolder(var binding: ItemTopNewsBinding) : RecyclerView.ViewHolder(binding.root)

    class PopularNewsViewHolder(var binding: ItemPopularNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            Model.HEADER_TEXT_TYPE -> {
                val binding =
                    ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HeaderViewHolder(binding)
            }
            Model.TOP_NEWS_TYPE -> {
                val binding =
                    ItemTopNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return TopNewsViewHolder(binding)
            }
            else -> {
                val binding = ItemPopularNewsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return PopularNewsViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataSet[position].type) {
            0 -> Model.HEADER_TEXT_TYPE
            1 -> Model.TOP_NEWS_TYPE
            2 -> Model.POPULAR_NEWS_TYPE
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateData(data: ArrayList<Model>) {
        dataSet = data
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model: Model = dataSet[position]
        holder.itemView.setOnClickListener {
            listItemClickListener.onItemClick(model, position)
        }
        when (model.type) {
            Model.HEADER_TEXT_TYPE -> (holder as HeaderViewHolder?)?.binding?.newsTopHeader?.text =
                model.text
            Model.TOP_NEWS_TYPE -> {
                (holder as TopNewsViewHolder?)?.binding?.let { topNewsViewHolder ->
                    topNewsViewHolder.newsHeader.text = model.text
                    Glide.with(topNewsViewHolder.imageView.context)
                        .load(model.data?.urlToImage.orEmpty())
                        .placeholder(Utils.randomDrawableColor).into(topNewsViewHolder.imageView)
                    topNewsViewHolder.newsHeader.text = model.data?.title.orEmpty()
                    topNewsViewHolder.newsDescription.text = model.data?.description.orEmpty()
                    topNewsViewHolder.newsChannel.text = model.data?.source?.name.orEmpty()
                }
            }
            Model.POPULAR_NEWS_TYPE -> {
                (holder as PopularNewsViewHolder?)?.binding?.let { popularNewsViewHolder ->
                    popularNewsViewHolder.newsHeader.text = model.text
                    Glide.with(popularNewsViewHolder.imageView.context)
                        .load(model.data?.urlToImage.orEmpty())
                        .placeholder(Utils.randomDrawableColor)
                        .into(popularNewsViewHolder.imageView)
                    popularNewsViewHolder.newsHeader.text = model.data?.title.orEmpty()
                    popularNewsViewHolder.newsDescription.text = model.data?.description.orEmpty()
                    popularNewsViewHolder.newsChannel.text = model.data?.source?.name.orEmpty()
                }
            }
        }
    }
}
