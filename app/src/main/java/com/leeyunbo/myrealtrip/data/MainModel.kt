package com.leeyunbo.myrealtrip.data

import androidx.databinding.ObservableArrayList
import com.leeyunbo.myrealtrip.parser.NewsXmlParser
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/*
 * ViewModel에게 데이터 요청을 받으면, 인터넷 접근 하여 데이터를 얻어온다
 * 데이터를 얻으면 ViewModel에게 직접 전달한다.
 */

class MainModel {
    val urlString = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    lateinit var result : ObservableArrayList<News>
    fun loadNewsData() : ObservableArrayList<News> {
        result = downloadUrl()?.use {
            NewsXmlParser.parse(it)
        } ?: ObservableArrayList()
        return result
    }

    private fun downloadUrl() : InputStream? {
        val url = URL(urlString)
        return (url.openConnection() as? HttpURLConnection)?.run {
               readTimeout = 25000
               connectTimeout = 15000
               requestMethod = "GET"
               doInput = true
               connect()
               inputStream
        }
    }
}