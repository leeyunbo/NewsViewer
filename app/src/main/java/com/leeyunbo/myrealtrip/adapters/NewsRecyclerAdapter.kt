package com.leeyunbo.myrealtrip.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.R
import com.leeyunbo.myrealtrip.databinding.NewsItemBinding
import com.leeyunbo.myrealtrip.data.News

/*
 * News RecyclerView를 위한 어댑터 구현
 */
class NewsDataAdapter : RecyclerView.Adapter<ViewHolder>() {
    var items : ArrayList<News> = ArrayList()
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        System.out.println(items.get(position))
        holder.bind(items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun updateItems(_items : ArrayList<News>) {
        val callback = RecyclerDiffCallback(this.items,_items)
        val result : DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        this.items.clear()
        this.items.addAll(_items)
        result.dispatchUpdatesTo(this)
    }
}

class ViewHolder(private val binding : NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(items : News?) {
        binding.apply {
            news = items
        }
    }
}