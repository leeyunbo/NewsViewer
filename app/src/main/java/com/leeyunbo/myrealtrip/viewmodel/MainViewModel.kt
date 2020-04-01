package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leeyunbo.myrealtrip.datamodel.MainModel
import com.leeyunbo.myrealtrip.dataclass.News

class MainViewModel : ViewModel() {
    var newsList : ObservableArrayList<News> = ObservableArrayList()

    val model : MainModel by lazy {
        MainModel()
    }

    suspend fun doAction() {
        System.out.println("doAction()")
        this.newsList = model.loadNewsData()
    }

}