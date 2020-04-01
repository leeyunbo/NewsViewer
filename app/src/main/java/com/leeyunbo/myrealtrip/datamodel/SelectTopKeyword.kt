package com.leeyunbo.myrealtrip.datamodel

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

object SelectTopKeyword {
    fun getTopKeywords(description : String) : ArrayList<String>? {
        val wordList = description.split(" ")
        val map = wordList
            .asSequence()
            .groupingBy { it }
            .eachCount()

        val keywordPQueue : PriorityQueue<Map.Entry<String,Int>> =  PriorityQueue(map.size,Keyword.KeywordComparator)

        for(entry in map.entries) keywordPQueue.add(entry)


        var size : Int = keywordPQueue.size
        if(keywordPQueue.size > 3) size = 3

        val keywords = ArrayList<String>()
        for(keywordsCnt in 0..size) keywords.add(keywordPQueue.poll().key)

        return keywords
    }
}

class Keyword {
    object KeywordComparator : Comparator<Map.Entry<String,Int>> {
        override fun compare(o1: Map.Entry<String, Int>?, o2: Map.Entry<String, Int>?): Int =
            o1!!.value.compareTo(o2!!.value)
    }
}