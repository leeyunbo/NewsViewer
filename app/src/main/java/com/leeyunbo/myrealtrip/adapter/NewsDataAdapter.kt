package com.leeyunbo.myrealtrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.R
import com.leeyunbo.myrealtrip.dataclass.News
import kotlinx.android.synthetic.main.news_item.view.*

/*
 * News RecyclerView를 위한 어댑터 구현
 */
class NewsDataAdapter(val context : Context) : RecyclerView.Adapter<ViewHolder>() {
    var items : List<News> = emptyList()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(
            R.layout.news_item,
            parent,
            false
        ))
    }


}

class ViewHolder (view : View) : RecyclerView.ViewHolder(view) {
    val itemTitleTv = view.item_title_tv
    val itemContentTv = view.item_content_tv
    val itemKeyword01 = view.item_keyword_tv1
    val itemKeyword02 = view.item_keyword_tv2
    val itemKeyword03 = view.item_keyword_tv3
}