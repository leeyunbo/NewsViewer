package com.leeyunbo.myrealtrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.leeyunbo.myrealtrip.databinding.ActivityDetailBinding

/*
 * 클릭한 News의 자세한 내용을 보여주는 액티비티 구현
 */
class DetailActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail)
        mBinding.apply {
            items = intent.getParcelableExtra("news")
        }
    }
}
