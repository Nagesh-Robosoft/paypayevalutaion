package com.test.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.newsapp.ui.model.Model
import com.test.newsapp.utils.Utils
import com.test.pokemongo.R

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

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsTopHeader: TextView = itemView.findViewById(R.id.newsTopHeader)
    }

    class TopNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsHeader: TextView = itemView.findViewById(R.id.newsHeader)
        var image: ImageView = itemView.findViewById(R.id.imageView) as ImageView
        var newsDescription: TextView = itemView.findViewById(R.id.newsDescription)
        var newsChannel: TextView = itemView.findViewById(R.id.newsChannel)
    }

    class PopularNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsHeader: TextView = itemView.findViewById(R.id.newsHeader)
        var image: ImageView = itemView.findViewById(R.id.imageView) as ImageView
        var newsDescription: TextView = itemView.findViewById(R.id.newsDescription)
        var newsChannel: TextView = itemView.findViewById(R.id.newsChannel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            Model.HEADER_TEXT_TYPE -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                return HeaderViewHolder(view)
            }
            Model.TOP_NEWS_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_top_news, parent, false)
                return TopNewsViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_popular_news, parent, false)
                return PopularNewsViewHolder(view)
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
            Model.HEADER_TEXT_TYPE -> (holder as HeaderViewHolder?)?.newsTopHeader?.text =
                model.text
            Model.TOP_NEWS_TYPE -> {
                (holder as TopNewsViewHolder?)!!.newsHeader.text = model.text
                Glide.with(holder.image.context).load(model.data?.urlToImage.orEmpty())
                    .placeholder(Utils.randomDrawableColor).into(holder.image)
                holder.newsHeader.text = model.data?.title.orEmpty()
                holder.newsDescription.text = model.data?.description.orEmpty()
                holder.newsChannel.text = holder.image.context.getString(R.string.cnn)
            }
            Model.POPULAR_NEWS_TYPE -> {
                (holder as PopularNewsViewHolder?)?.newsHeader?.text = model.text
                Glide.with(holder.image.context).load(model.data?.urlToImage.orEmpty())
                    .placeholder(Utils.randomDrawableColor).into(holder.image)
                holder.newsHeader.text = model.data?.title.orEmpty()
                holder.newsDescription.text = model.data?.description.orEmpty()
                holder.newsChannel.text = holder.image.context.getString(R.string.cnn)
            }
        }
    }
}
