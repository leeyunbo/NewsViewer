package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leeyunbo.myrealtrip.dataclass.MainModel
import com.leeyunbo.myrealtrip.dataclass.News
import java.util.*

class MainViewModel {
    val newsList : MutableLiveData<List<News>> by lazy {
        MutableLiveData<List<News>>()
    }

    val model : MainModel by lazy {
        MainModel(this)
    }


    fun getNewsList() {
        this.newsList.postValue(model.loadNewsData())
    }

}