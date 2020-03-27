package com.leeyunbo.myrealtrip.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leeyunbo.myrealtrip.datamodel.MainModel
import com.leeyunbo.myrealtrip.dataclass.News

class MainViewModel : ViewModel() {
    val newsList : MutableLiveData<List<News>> by lazy {
        MutableLiveData<List<News>>()
    }

    val model : MainModel by lazy {
        MainModel()
    }


    suspend fun doAction() {
        this.newsList.postValue(model.loadNewsData())
    }

}