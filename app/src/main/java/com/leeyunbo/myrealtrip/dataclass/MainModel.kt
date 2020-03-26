package com.leeyunbo.myrealtrip.dataclass

import com.leeyunbo.myrealtrip.datamodel.NewsXmlParser
import com.leeyunbo.myrealtrip.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.async
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainModel(val viewModel : MainViewModel) {
    val urlString = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    suspend fun loadNewsData() : List<News> {
        return downloadUrl()?.use {
            NewsXmlParser.parse(it)
        } ?: emptyList()
    }

    private suspend fun downloadUrl() : InputStream? {
        val url = URL(urlString)
        var result : InputStream? = null
        val deferred = GlobalScope.async (Dispatchers.IO) {
            result = (url.openConnection() as? HttpURLConnection)?.run {
                readTimeout = 10000
                connectTimeout = 15000
                requestMethod = "GET"
                doInput = true
                connect()
                inputStream
            }
        }

        deferred.await()

        return result
    }
}