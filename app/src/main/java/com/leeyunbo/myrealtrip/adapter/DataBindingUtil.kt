package com.leeyunbo.myrealtrip.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.dataclass.News

@BindingAdapter("bind_items")
fun setBindItems(view : RecyclerView, items : List<News>) {
    val adapter = view.adapter as? NewsDataAdapter ?: NewsDataAdapter().apply {

    }
}