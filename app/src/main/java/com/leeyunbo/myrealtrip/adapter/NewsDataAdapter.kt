package com.leeyunbo.myrealtrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.R
import com.leeyunbo.myrealtrip.databinding.NewsItemBinding
import com.leeyunbo.myrealtrip.dataclass.News
import kotlinx.android.synthetic.main.news_item.view.*

/*
 * News RecyclerView를 위한 어댑터 구현
 */
class NewsDataAdapter() : RecyclerView.Adapter<ViewHolder>() {
    var items : List<News> = emptyList()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.news_item,
            parent,
            false
        ))
    }
}

class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val binding : NewsItemBinding = NewsItemBinding()

    fun bind(news : News) {
        binding.setNews(news)
    }
}