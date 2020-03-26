package com.leeyunbo.myrealtrip.datamodel

import android.util.Xml
import com.leeyunbo.myrealtrip.dataclass.News
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.lang.IllegalStateException

/*
 * title : item > title
 * image : item > link, <meta property="og:image"
 * keyword : item > link, <meta property="og:description"
 */

object NewsXmlParser {
    fun parse(inputStream: InputStream) : ArrayList<News> {
        inputStream.use {
            val parser : XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(it,null)
            parser.nextTag()
            return readFeed(parser)
        }
    }

    private fun readFeed(parser : XmlPullParser) : ArrayList<News> {
        val newsList = ArrayList<News>()

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

    private fun readNews(parser : XmlPullParser) : News {
        parser.require(XmlPullParser.START_TAG, null, "item")
        lateinit var title : String
        lateinit var content : String
        lateinit var keyword : ArrayList<String>
        lateinit var image : String
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "title" -> title = readTitle(parser)
                "link" -> {
                    val link = readLink(parser)
                    content = readContent(link)
                    keyword = readKeyword(link)
                    image = readImage(link)
                }
            }
        }
        return News(title,content,keyword,image)
    }

    private fun readTitle(parser : XmlPullParser) : String {
        var title = ""
        parser.require(XmlPullParser.START_TAG, null, "title")
        title = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, "title")
        return title
    }


    private fun readLink(parser : XmlPullParser) : String {
        var link = ""
        parser.require(XmlPullParser.START_TAG, null, "link")
        val tag = parser.name
        val relType = parser.getAttributeValue(null, "rel")
        if (tag == "link") {
            if (relType == "alternate") {
                link = parser.getAttributeValue(null, "href")
                parser.nextTag()
            }
        }
        parser.require(XmlPullParser.END_TAG, null, "link")
        return link
    }

    private fun readImage(link : String) : String {
        var image = ""
        return image
    }

    private fun readContent(link : String) : String {
        var content = ""
        return content
    }

    private fun readKeyword(link : String) : ArrayList<String> {
        var keyword = ArrayList<String>()
        return keyword

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