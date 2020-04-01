package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.leeyunbo.myrealtrip.data.MainModel
import com.leeyunbo.myrealtrip.data.News

class MainViewModel : ViewModel() {
    var items : ObservableArrayList<News> = ObservableArrayList()

    init {
        items.add(News("hello","hello",null,null))
        items.add(News("hello","hello",null,null))
        items.add(News("hello","hello",null,null))
        items.add(News("hello","hello",null,null))
        items.add(News("hello","hello",null,null))
    }

    val model : MainModel by lazy {
        MainModel()
    }

    suspend fun doAction() {
        this.items = model.loadNewsData()
        System.out.println("change NewsList")
    }

}