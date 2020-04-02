package com.leeyunbo.myrealtrip.util

import android.webkit.WebView
import android.webkit.WebViewClient

class NewsWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url)
        return true
    }
}