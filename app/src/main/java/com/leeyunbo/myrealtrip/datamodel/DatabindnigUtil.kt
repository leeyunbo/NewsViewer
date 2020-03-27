package com.leeyunbo.myrealtrip.datamodel

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.adapter.NewsDataAdapter
import com.leeyunbo.myrealtrip.dataclass.News

@BindingAdapter("app:bindItems")
fun bindItems(view : RecyclerView, items : ArrayList<News>) {
    val adapter = view.adapter as? NewsDataAdapter ?: NewsDataAdapter().apply {
        view.adapter = this
    }
    adapter.items = items
    adapter.notifyDataSetChanged()
}

@BindingAdapter("app:bindImage")
fun bindImage(view : ImageView, link : String) {
    view.setImageURI(Uri.parse(link))
}