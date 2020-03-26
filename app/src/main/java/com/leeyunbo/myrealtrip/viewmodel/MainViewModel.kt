package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.leeyunbo.myrealtrip.dataclass.MainModel
import com.leeyunbo.myrealtrip.dataclass.News
import java.util.*

class MainViewModel {
    val newsList = ObservableArrayList<News>()
    val model : MainModel = MainModel(this)


    fun setNewsList() = {
        newsList = 
    }
}