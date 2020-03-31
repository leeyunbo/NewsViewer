package com.leeyunbo.myrealtrip.datamodel

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

object SelectTopKeyword {
    fun getTopKeywords(description : String) : ArrayList<String> {
        val topKeywords = ArrayList<String>()
        val wordList = description.split(" ")
        val map = wordList
            .asSequence()
            .groupingBy { it }
            .eachCount()

        val kFrequent : PriorityQueue<Map.Entry<String,Int>> =  PriorityQueue(map.size,Keyword.KeywordComparator)

        for(entry in map.entries) kFrequent.add(entry)

        for(i in 0..3) topKeywords.add(kFrequent.poll().key)

        return topKeywords
    }
}

class Keyword {
    object KeywordComparator : Comparator<Map.Entry<String,Int>> {
        override fun compare(o1: Map.Entry<String, Int>?, o2: Map.Entry<String, Int>?): Int =
            o1!!.value.compareTo(o2!!.value)
    }
}