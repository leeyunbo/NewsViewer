package com.leeyunbo.myrealtrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.leeyunbo.myrealtrip.adapters.NewsDataAdapter
import com.leeyunbo.myrealtrip.databinding.ActivityMainBinding
import com.leeyunbo.myrealtrip.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

/*
 * News 목록을 보여주는 액티비티 구현
 */
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var mViewModel : MainViewModel
    lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setRefreshLayout()
        mViewModel = MainViewModel()
        showNewsList(mViewModel)
        mBinding.apply {
            vm = mViewModel
        }
        initRecyclerView()

    }

    override fun onRefresh() {
        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                showNewsList(mViewModel)
            }.await()
            main_activity_rf.isRefreshing = false
        }
    }

    fun setRefreshLayout() {
        main_activity_rf.setOnRefreshListener(this)
        main_activity_rf.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    fun initRecyclerView() {
        main_activity_rv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        main_activity_rv.setHasFixedSize(true)
        main_activity_rv.adapter = NewsDataAdapter()
    }

    /*
     * viewModel에게 데이터 최신화를 요청하는 메서드
     */
    fun showNewsList(viewModel : MainViewModel){
        CoroutineScope(Dispatchers.Main).launch {
            main_activity_rf.isRefreshing = true
            CoroutineScope(Dispatchers.IO).async {
                viewModel.doAction()
            }.await()
            main_activity_rf.isRefreshing = false
        }
    }


}
