package com.leeyunbo.myrealtrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.R
import com.leeyunbo.myrealtrip.databinding.NewsItemBinding
import com.leeyunbo.myrealtrip.dataclass.News
import kotlinx.android.synthetic.main.news_item.view.*

/*
 * News RecyclerView를 위한 어댑터 구현
 */
class NewsDataAdapter : RecyclerView.Adapter<ViewHolder>() {
    var items : ObservableArrayList<News> = ObservableArrayList()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val binding : NewsItemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,view)
    }
}

class ViewHolder(val binding : NewsItemBinding, view : View) : RecyclerView.ViewHolder(view){
    fun bind(news : News?) {
        binding.news = news
    }
}