package com.leeyunbo.myrealtrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.adapter.NewsDataAdapter
import com.leeyunbo.myrealtrip.databinding.ActivityMainBinding
import com.leeyunbo.myrealtrip.dataclass.News
import com.leeyunbo.myrealtrip.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
 * News 목록을 보여주는 액티비티 구현
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : MainViewModel by lazy {
            MainViewModel()
        }
        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.setVm(viewModel)
        showNewsList(viewModel)
    }

    fun showNewsList(viewModel : MainViewModel) {
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.doAction()
        }
    }

    @BindingAdapter("app:bindItems")
    fun bindItems(view : RecyclerView, items : ArrayList<News>) {
        val adapter = view.adapter as? NewsDataAdapter ?: NewsDataAdapter(this).apply {
            view.adapter = this
        }
        adapter.items = items
        adapter.notifyDataSetChanged()
    }
}
