package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.leeyunbo.myrealtrip.data.MainModel
import com.leeyunbo.myrealtrip.data.News

class MainViewModel : ViewModel() {
    var items : ObservableArrayList<News> = ObservableArrayList()

    val model : MainModel by lazy {
        MainModel()
    }

    fun doAction() {
        model.loadNewsData().let {
            items.clear()
            items.addAll(it)
        }
    }
}