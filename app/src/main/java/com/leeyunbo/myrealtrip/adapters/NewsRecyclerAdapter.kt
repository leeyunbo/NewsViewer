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
 * 만약 뉴스 데이터들을 다시 불러와 ViewModel 데이터가 최신화 된다면, data binding을 통해서 items도 최신화됨
 */

class NewsDataAdapter : RecyclerView.Adapter<BindingViewHolder>() {
    var items : ArrayList<News> = ArrayList()
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.bind(items.get(position))
        System.out.println(items.get(position).keywords?.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun updateItems(_items : ArrayList<News>) {
        val callback = RecyclerDiffCallback(this.items, _items)
        val result : DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        this.items.clear()
        this.items.addAll(_items)
        result.dispatchUpdatesTo(this)
    }
}

class BindingViewHolder(private val mBinding : NewsItemBinding) : RecyclerView.ViewHolder(mBinding.root){
    fun bind(items : News?) {
        mBinding.apply {
            news = items
        }
        mBinding.root.setOnClickListener { view ->
            val detailIntent = Intent(view.context, DetailActivity::class.java)
            detailIntent.putExtra("news",items)
            view.context.startActivity(detailIntent)
        }
    }
}