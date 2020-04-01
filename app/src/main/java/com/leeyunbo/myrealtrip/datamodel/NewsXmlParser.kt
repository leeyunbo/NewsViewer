package com.leeyunbo.myrealtrip.datamodel

import android.util.Xml
import androidx.databinding.ObservableArrayList
import com.leeyunbo.myrealtrip.dataclass.News
import org.xmlpull.v1.XmlPullParser
import java.io.BufferedReader
import java.io.InputStream
import java.lang.IllegalStateException

/*
 * title : item > title
 * image : item > link, <meta property="og:image"
 * keyword : item > link, <meta property="og:description"
 */

object NewsXmlParser {
    fun parse(inputStream: InputStream) : ObservableArrayList<News>? {
        System.out.println("parse()")
        val parser : XmlPullParser = Xml.newPullParser()
        parser.apply {
            setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            setInput(inputStream,null)
            next()
        }
        return readFeed(parser)
    }

    //1. START_TAG를 만났을 때, item이면 파싱을 시작(readNews())
    private fun readFeed(parser : XmlPullParser) : ObservableArrayList<News>? {
        System.out.println("readFeed()")
        val newsList = ObservableArrayList<News>()

        parser.require(XmlPullParser.START_TAG,null,"item")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }

            if (parser.name == "item") {
                newsList.add(readNews(parser))
            } else {
                skip(parser)
            }
        }
        return newsList
    }

    //2. END_TAG를 만날때까지(</item>) 나오는 태그들에 대하여, 동작을 진행한다.
    private fun readNews(parser : XmlPullParser) : News {
        parser.require(XmlPullParser.START_TAG, null, "item")
        lateinit var title : String
        var content : String? = null
        var image : String? = null
        var keyword : ArrayList<String>? = null
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                //2-1. <title>이면 title 데이터를 읽어온다(readTitle())
                "title" -> title = readTitle(parser)
                //2-2. <link>이면 link 데이터를 읽어온다(readLink())
                "link" -> {
                    val link = readLink(parser)
                    val map = readOther(link)
                    content = map.get("content")
                    image = map.get("image")
                    if(content != null) keyword = SelectTopKeyword.getTopKeywords(content)
                }
            }
        }
        return News(title,content,keyword,image)
    }

    // <title>여권 “채널A-검찰 유착 의혹에 윤석열 총장 입장 밝혀라” - 미디어오늘</title>
    private fun readTitle(parser : XmlPullParser) : String {
        parser.require(XmlPullParser.START_TAG, null, "title")
        val title = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "title")
        return title
    }


    // <link> https://news.google.com/__i/rss/rd/articles </link>
    private fun readLink(parser : XmlPullParser) : String {
        parser.require(XmlPullParser.START_TAG, null, "link")
        val link = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "link")
        return link
    }

    // <meta name ="og:image" content="">
    // <meta name ="og:description" content="">
    private fun readOther(link : String) : HashMap<String, String> {
        val map = MetaTagsParser.parseMetaTags(link)
        return map
    }

    private fun readText(parser : XmlPullParser) : String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.nextText()
            parser.nextTag()
        }
        return result
    }

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