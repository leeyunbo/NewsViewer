package com.leeyunbo.myrealtrip.adapters

import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leeyunbo.myrealtrip.R
import com.leeyunbo.myrealtrip.data.News
import com.leeyunbo.myrealtrip.util.NewsWebViewClient
import java.lang.IndexOutOfBoundsException

/*
 * Data Binding Adapter
 */
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
    try {
        view.text = keywords[position-1]
    } catch(e: IndexOutOfBoundsException) {
        view.visibility = View.INVISIBLE
    }
}

@BindingAdapter("bind_url")
fun bindUrl(view : WebView, url : String) {
    view.settings.apply {
        javaScriptEnabled = true
        javaScriptCanOpenWindowsAutomatically = false
        loadWithOverviewMode = true
        useWideViewPort = true
        builtInZoomControls = false
        layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        cacheMode = WebSettings.LOAD_NO_CACHE
        domStorageEnabled = true
        setSupportZoom(false)
        setSupportMultipleWindows(true)
    }
        view.webViewClient = NewsWebViewClient()
        view.loadUrl(url)
}