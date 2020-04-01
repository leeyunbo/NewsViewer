package com.leeyunbo.myrealtrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leeyunbo.myrealtrip.adapter.NewsDataAdapter
import com.leeyunbo.myrealtrip.databinding.ActivityMainBinding
import com.leeyunbo.myrealtrip.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
 * News 목록을 보여주는 액티비티 구현
 */
class MainActivity : AppCompatActivity() {
    lateinit var viewModel : MainViewModel
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = MainViewModel()

        initRecyclerView()
        showNewsList(viewModel)
        binding.vm = viewModel

    }

    fun initRecyclerView() {
        main_activity_rv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        main_activity_rv.setHasFixedSize(true)
        main_activity_rv.adapter = NewsDataAdapter()
    }

    fun showNewsList(viewModel : MainViewModel) {
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.doAction()
        }
    }
}
