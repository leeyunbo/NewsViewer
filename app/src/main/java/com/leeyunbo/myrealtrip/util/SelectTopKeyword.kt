package com.leeyunbo.myrealtrip.util

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

object SelectTopKeyword {
    fun getTopKeywords(description : String) : ArrayList<String>? {
        val st = StringTokenizer(description,"`“”’‘<>!@#$%^&*(),./?:;][=-…\'\"\t◆·\n ")
        val wordList : List<String> = st.toList() as List<String>
        val pairList : List<Pair<String,Int>> = wordList
            .filter { it.length >= 2 }
            .asSequence()
            .groupingBy { it }
            .eachCount()
            .toList()

        Collections.sort(pairList, Comparators.keyComparator)
        Collections.sort(pairList, Comparators.valueComparator)

        for(pair in pairList) {
            print("${pair.toString()} ")
        }


        var size : Int = pairList.size
        if(size > 3) size = 3

        val keywords = ArrayList<String>()
        var pairIdx = 0
        for(pair in pairList) {
            if(pairIdx == size) break
            keywords.add(pair.first)
            pairIdx++
        }

        return keywords
    }
}

class Comparators {
    object valueComparator : Comparator<Pair<String,Int>> {
        override fun compare(o1: Pair<String, Int>?, o2: Pair<String, Int>?): Int {
            if(o1!!.second > o2!!.second) {
                return -1
            }
            else if(o1!!.second < o2!!.second) {
                return 1
            }
            else return 0
        }
    }

    object keyComparator : Comparator<Pair<String,Int>> {
        override fun compare(o1: Pair<String, Int>?, o2: Pair<String, Int>?): Int {
            if(o1!!.first > o2!!.first) {
                return 1
            }
            else if(o1!!.first < o2!!.first) {
                return -1
            }
            else return 0
        }
    }
}