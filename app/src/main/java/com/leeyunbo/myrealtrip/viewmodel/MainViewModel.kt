package com.leeyunbo.myrealtrip.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.leeyunbo.myrealtrip.data.MainModel
import com.leeyunbo.myrealtrip.data.News
/*
 * Recyclerview에 담을 데이터 리스트를 가지고 있는 ViewModel
 * MainView에서 요청이 들어오면 MainModel에게 데이터 가져올 것을 요청
 * MainView에서 관찰하고 있다가 ViewModel의 데이터가 변경되면 data binding을 통해 UI 변경
 */
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