package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import java.util.*

class MainViewModel {
    val title = ObservableField("")
    val content = ObservableField("")
    val keywordList : MutableList<String> = ObservableArrayList<String>()
}