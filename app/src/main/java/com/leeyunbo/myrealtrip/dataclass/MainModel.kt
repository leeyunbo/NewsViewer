package com.leeyunbo.myrealtrip.dataclass

import androidx.databinding.ObservableArrayList
import com.leeyunbo.myrealtrip.datamodel.NewsXmlParser
import com.leeyunbo.myrealtrip.viewmodel.MainViewModel
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainModel(val viewModel : MainViewModel) {
    val urlString = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    fun loadNewsData() : List<News> {
        val xmlParser = NewsXmlParser()

        return downloadUrl()?.use {
            xmlParser.parse(it)
        } ?: emptyList()
    }

    private fun downloadUrl() : InputStream? {
        val url = URL(urlString)
        return (url.openConnection() as? HttpURLConnection)?.run {
            readTimeout = 10000
            connectTimeout = 15000
            requestMethod = "GET"
            doInput = true
            connect()
            inputStream
        }
    }
}