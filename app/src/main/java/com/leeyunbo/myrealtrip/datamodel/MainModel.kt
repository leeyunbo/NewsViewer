package com.leeyunbo.myrealtrip.datamodel

import androidx.databinding.ObservableArrayList
import com.leeyunbo.myrealtrip.dataclass.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainModel() {
    val urlString = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    lateinit var result : ObservableArrayList<News>
    suspend fun loadNewsData() : ObservableArrayList<News> {
        val deffered = GlobalScope.async (Dispatchers.IO) {
            result = downloadUrl()?.use {
                NewsXmlParser.parse(it)
            } ?: ObservableArrayList()
        }
        deffered.await()
        return result
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