package com.leeyunbo.myrealtrip.parser

import android.util.Xml
import androidx.databinding.ObservableArrayList
import com.leeyunbo.myrealtrip.data.News
import com.leeyunbo.myrealtrip.util.SelectTopKeyword
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

/*
 * title : item > title
 * image : item > link, <meta property="og:image"
 * keyword : item > link, <meta property="og:description"
 * keyword : item > link, <meta name ="description"
 * Xml Parser, Link를 얻어내게 되면 Html Parser에게 전달한다.
 */

object NewsXmlParser {
    private val ns : String? = null
    @Throws(XmlPullParserException::class, IOException::class)
     fun parse(inputStream: InputStream) : ObservableArrayList<News> {
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(it, null)
            parser.nextTag()
            return readNews(parser)
        }
    }

    //1. rss 태그로 이동 후, item 태그 search
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readNews(parser : XmlPullParser) : ObservableArrayList<News> {
        var newsList = ObservableArrayList<News>()
            parser.require(XmlPullParser.START_TAG, ns, "rss")
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.eventType != XmlPullParser.START_TAG) {
                    continue
                }
                if (parser.name == "item") {
                    newsList.add(readItem(parser))
                }

            }
        return newsList
    }

    //2. item 태그 발견 후, item 내부의 태그 파싱
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readItem(parser : XmlPullParser) : News {
        var title: String? = null
        var description: String? = null
        var imageUrl: String? = null
        var keywords: ArrayList<String>? = null
        lateinit var link : String
        var reg = Regex("[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\s]")
        var regSpace = Regex("\\s{2,}")
        var regSpaceConfirm = Regex("\\s\\s+")
        var regSpaceAll = Regex("\\p{Z}")
        var regSpaceDel = Regex("(^\\p{Z}+|\\p{Z}+$)")
        parser.require(XmlPullParser.START_TAG, ns,"item")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "title" -> title = readTitle(parser)
                "link" -> {
                    link = readLink(parser)
                    val map = readOther(link)
                    description = map.get("description")?.apply {
                        trim()
                        replace(reg," ")
                        replace(regSpace,"")
                        replace(regSpaceConfirm,"")
                        replace(regSpaceAll,"")
                        replace(regSpaceDel,"")
                    }
                    imageUrl = map.get("image")

                    if (description != null) {
                        keywords = SelectTopKeyword.getTopKeywords(description)
                    }
                    else {
                        keywords = ArrayList()
                        description = "내용이 없음"
                    }
                }
            }
        }
        return News(title, description, keywords, imageUrl,link)
    }



    // <title> 제목 </title>
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

    // <meta property ="og:image" content="">
    // <meta property ="og:description" content="">
    // <meta name ="description" content="">
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readOther(link : String) : HashMap<String, String> {
        return MetaTagsParser.parseMetaTags(link)
    }

    // <태그> 텍스트 </태그>
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readText(parser : XmlPullParser) : String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

}