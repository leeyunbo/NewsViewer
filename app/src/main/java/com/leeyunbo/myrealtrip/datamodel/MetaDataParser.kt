package com.leeyunbo.myrealtrip.datamodel

import android.util.Log
import com.leeyunbo.myrealtrip.dataclass.News
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

/*
 * title : item > title
 * image : item > link, <meta property="og:image"
 * keyword : item > link, <meta property="og:description"
 */

object MetaDataParser {
    fun getMetaData(url : String, news : News) {
        try {
            val dataList: ArrayList<String> = ArrayList()
            val doc: Document = Jsoup.connect(url).get()
            val ogTags: Elements = doc.select("meta[property^=og:]") ?: return

            ogTags.forEach { element ->
                var text = element.attr("property")
                when {
                    text == "og:image" -> {
                        news.image = element.attr("content")
                    }
                    text == "og:description" -> {
                        news.content = element.attr("content")
                        news.keyword = SelectTopKeyword.getTopKeywords(news.content)
                    }
                }
            }
        } catch(e:IOException) {
            Log.e("IOException","MetaDataPraser.getMetadata()")
            e.printStackTrace()
            return
        } catch(e:IllegalAccessException) {
            Log.e("IllegalAccessException","MetaDataParser.getMetaData()")
            e.printStackTrace()
            return
        }

    }
}