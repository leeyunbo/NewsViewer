package com.leeyunbo.myrealtrip.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leeyunbo.myrealtrip.R
import com.leeyunbo.myrealtrip.data.News

@BindingAdapter("bind_items")
fun bindItems(view : RecyclerView, items : ObservableArrayList<News>) {
    val adapter = view.adapter as? NewsDataAdapter ?: NewsDataAdapter().apply {
        view.adapter = this
    }
    adapter.updateItems(items)
}

@BindingAdapter("bind_image")
fun bindImage(view : ImageView, imageUrl : String?) {
    if(imageUrl == null) view.setImageResource(R.drawable.ic_no_image)
    else Glide.with(view.context)
        .load(imageUrl)
        .centerCrop()
        .error(R.drawable.ic_no_image)
        .into(view)
}

@BindingAdapter("bind_keyword","keyword_position")
fun bindKeyword(view : TextView, keywords : ArrayList<String>, position : Int) {
    if(keywords.size >= position) {
        view.text = keywords.get(position-1)
    }
}