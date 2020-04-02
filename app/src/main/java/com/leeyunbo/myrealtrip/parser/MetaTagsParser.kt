package com.leeyunbo.myrealtrip.parser

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


/*
 * title : item > title
 * image : item > link, <meta property="og:image"
 * keyword : item > link, <meta property="og:description"
 */

object MetaTagsParser {
    var trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
        override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
    })

    fun parseMetaTags(url : String) : HashMap<String,String> {
        val sc : SSLContext = SSLContext.getInstance("SSL")
        val resultMap : HashMap<String,String> = HashMap()
        sc.init(null, trustAllCerts, SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
        try {
            val doc: Document = Jsoup.connect(url).get()
            val ogTags: Elements = doc.select("meta[property^=og:]")
            val nextOgTags : Elements = doc.select("meta[name]")

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

            if(!resultMap.containsKey("description")) {
                nextOgTags.forEach {element ->
                    var name = element.attr("name")
                    when {
                        name == "description" -> {
                            resultMap.put("description", element.attr("content"))
                        }
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