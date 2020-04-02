package com.leeyunbo.myrealtrip.util

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
/*
 * 가장 많이 나타난 Keyword를 뽑아 내기 위한 분석 클래스
 * Collections.sort() 는 Stable sort이기 때문에 사용 가능
 * key , value 순으로 정렬 후 최대 상위 3개 데이터 추출
 * 만약 3개보다 적다면, 리스트의 size만큼 데이터 추출
 */
object SelectTopKeyword {
    fun getTopKeywords(description : String) : ArrayList<String>? {
        val st = StringTokenizer(description," \uD83D\uDC4D\uD83C\uDFFB∼`“”’‘<>!@#$%^&*(),./?~:;][=-…\'\"\t◆·\n ")
        val wordList : List<String> = st.toList() as List<String>
        val pairList : List<Pair<String,Int>> = wordList
            .filter { it.length >= 2 }
            .asSequence()
            .groupingBy { it }
            .eachCount()
            .toList()

        Collections.sort(pairList, Comparators.keyComparator)
        Collections.sort(pairList, Comparators.valueComparator)

        var size : Int = pairList.size
        if(size > 3) size = 3

        val topKeywords = ArrayList<String>()
        var pairIdx = 0
        for(pair in pairList) {
            if(pairIdx == size) break
            topKeywords.add(pair.first)
            pairIdx++
        }

        return topKeywords
    }
}

// 특수한 인스턴스간 정렬을 위한 Comparator 선언
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