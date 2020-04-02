package com.leeyunbo.myrealtrip.parser

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

object SelectTopKeyword {
    fun getTopKeywords(description : String) : ArrayList<String>? {
        val st = StringTokenizer(description,"!@#$%^&*(),./?:;][=-…'\"\t◆ ")
        val wordList : List<String> = st.toList() as List<String>
        val map = wordList
            .filter { it.length >= 2 }
            .asSequence()
            .groupingBy { it }
            .eachCount()
            .toMap()

        System.out.println(map.toString())

        val keywordPQueue : PriorityQueue<Map.Entry<String,Int>> =  PriorityQueue(map.size,
            Keyword.KeywordComparator
        )

        for(entry in map.entries) keywordPQueue.add(entry)


        var size : Int = keywordPQueue.size-1
        if(size > 2) size = 2

        val keywords = ArrayList<String>()
        for(keywordsCnt in 0..size) keywords.add(keywordPQueue?.poll().key)

        return keywords
    }
}

class Keyword {
    object KeywordComparator : Comparator<Map.Entry<String,Int>> {
        override fun compare(o1: Map.Entry<String, Int>?, o2: Map.Entry<String, Int>?): Int =
            o1!!.value.compareTo(o2!!.value)
    }
}