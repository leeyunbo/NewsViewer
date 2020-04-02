package com.leeyunbo.myrealtrip.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.DetailActivity
import com.leeyunbo.myrealtrip.databinding.NewsItemBinding
import com.leeyunbo.myrealtrip.data.News
import com.leeyunbo.myrealtrip.util.RecyclerDiffCallback

/*
 * News RecyclerView를 위한 어댑터 구현
 */
class NewsDataAdapter : RecyclerView.Adapter<BindingViewHolder>() {
    var items : ArrayList<News> = ArrayList()
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun updateItems(_items : ArrayList<News>) {
        val callback = RecyclerDiffCallback(this.items, _items)
        val result : DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        System.out.println("updateItems() _items : ${_items.toString()}")

        this.items.clear()
        this.items.addAll(_items)
        result.dispatchUpdatesTo(this)
    }
}

class BindingViewHolder(private val binding : NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(items : News?) {
        binding.apply {
            news = items
        }

        binding.root.setOnClickListener { view ->
            val detailIntent = Intent(view.context, DetailActivity::class.java)
            detailIntent.putExtra("news",items)
            view.context.startActivity(detailIntent)
        }
    }
}