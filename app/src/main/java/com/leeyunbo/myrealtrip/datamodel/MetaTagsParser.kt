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

object MetaTagsParser {
    fun parseMetaTags(url : String) : HashMap<String,String> {
        val doc: Document = Jsoup.connect(url).get()
        val ogTags: Elements = doc.select("meta[property^=og:]")
        val resultMap : HashMap<String,String> = HashMap()

        try {
            ogTags.forEach { element ->
                var property = element.attr("property")
                when {
                    property == "og:image" -> {
                        resultMap.put("image",element.attr("content"))
                    }
                    property == "og:description" -> {
                        resultMap.put("description", element.attr("content"))
                    }
                }
            }
        } catch(e:IOException) {
            Log.e("IOException","MetaDataPraser.getMetadata()")
            e.printStackTrace()
        } catch(e:IllegalAccessException) {
            Log.e("IllegalAccessException","MetaDataParser.getMetaData()")
            e.printStackTrace()
        }

        return resultMap

    }
}