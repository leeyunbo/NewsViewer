package com.leeyunbo.myrealtrip.parser

import android.util.Xml
import androidx.databinding.ObservableArrayList
import com.leeyunbo.myrealtrip.data.News
import kotlinx.coroutines.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.lang.IllegalStateException

/*
 * title : item > title
 * image : item > link, <meta property="og:image"
 * keyword : item > link, <meta property="og:description"
 */

object NewsXmlParser {
    private val ns : String? = null
    @Throws(XmlPullParserException::class, IOException::class)
    suspend fun parse(inputStream: InputStream) : ObservableArrayList<News> {
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readNews(parser)
        }
    }

    //1. parsing 시작
    @Throws(XmlPullParserException::class, IOException::class)
    private suspend fun readNews(parser : XmlPullParser) : ObservableArrayList<News> {
        var newsList = ObservableArrayList<News>()
        var deffered : ArrayList<Job> = ArrayList()
        parser.require(XmlPullParser.START_TAG, ns,"rss")
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            if (parser.name == "item"){
                newsList.add(readItem(parser))
            }
        }
        return newsList
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readItem(parser : XmlPullParser) : News {
        parser.require(XmlPullParser.START_TAG, ns,"item")
        var title: String? = null
        var description: String? = null
        var imageUrl: String? = null
        var keywords: ArrayList<String>? = null
        lateinit var link : String
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                //2-1. <title>이면 title 데이터를 읽어온다(readTitle())
                "title" -> title = readTitle(parser)
                //2-2. <link>이면 link 데이터를 읽어온다(readLink())
                "link" -> {
                    link = readLink(parser)
                    val map = readOther(link)
                    description = map.get("description")
                    imageUrl = map.get("image")


                    if (description != null) keywords =
                        SelectTopKeyword.getTopKeywords(description)

                }
            }
        }
        System.out.println("keywords : ${keywords.toString()}, link : ${link}")
        return News(title, description, keywords, imageUrl)
    }



    // <title>여권 “채널A-검찰 유착 의혹에 윤석열 총장 입장 밝혀라” - 미디어오늘</title>
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readTitle(parser : XmlPullParser) : String {
        parser.require(XmlPullParser.START_TAG, null, "title")
        val title = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "title")
        return title
    }


    // <link> https://news.google.com/__i/rss/rd/articles </link>
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readLink(parser : XmlPullParser) : String {
        parser.require(XmlPullParser.START_TAG, null, "link")
        val link = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "link")
        return link
    }

    // <meta name ="og:image" content="">
    // <meta name ="og:description" content="">
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readOther(link : String) : HashMap<String, String> {
        return MetaTagsParser.parseMetaTags(link)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readText(parser : XmlPullParser) : String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser : XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}