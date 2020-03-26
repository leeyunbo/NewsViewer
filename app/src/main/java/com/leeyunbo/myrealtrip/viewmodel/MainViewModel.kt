package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.adapter.NewsDataAdapter
import com.leeyunbo.myrealtrip.dataclass.MainModel
import com.leeyunbo.myrealtrip.dataclass.News
import java.util.*

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