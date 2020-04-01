package com.leeyunbo.myrealtrip

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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
import java.net.URI
import java.net.URL

/*
 * News 목록을 보여주는 액티비티 구현
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()
        showNewsList(viewModel)

        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.vm = viewModel

    }

    fun showNewsList(viewModel : MainViewModel) {
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.doAction()
        }
    }
}
