package com.leeyunbo.myrealtrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.leeyunbo.myrealtrip.data.News
import com.leeyunbo.myrealtrip.databinding.ActivityDetailBinding

/*
 * 클릭한 News의 자세한 내용을 보여주는 액티비티 구현
 */
class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)
        binding.apply {
            news = intent.getParcelableExtra("news")
        }
    }
}
