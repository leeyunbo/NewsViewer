package com.leeyunbo.myrealtrip.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leeyunbo.myrealtrip.R
import com.leeyunbo.myrealtrip.adapter.NewsDataAdapter

@BindingAdapter("bind_items")
fun bindItems(view : RecyclerView, news : ObservableArrayList<News>) {
    val adapter = view.adapter as? NewsDataAdapter ?: NewsDataAdapter().apply {
        view.adapter = this
    }
    adapter.items = news
    adapter.notifyDataSetChanged()
}

@BindingAdapter("bind_image")
fun bindImage(view : ImageView, imageUrl : String?) {
    if(imageUrl == null) view.setImageResource(R.drawable.ic_no_image)
    else Glide.with(view.context).load(imageUrl).error(R.drawable.ic_no_image).into(view)
}