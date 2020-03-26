package com.leeyunbo.myrealtrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leeyunbo.myrealtrip.viewmodel.MainViewModel

/*
 * News 목록을 보여주는 액티비티 구현
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel = MainViewModel()
    }
}
