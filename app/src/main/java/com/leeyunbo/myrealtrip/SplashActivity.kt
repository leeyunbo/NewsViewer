package com.leeyunbo.myrealtrip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
/*
 * splash 화면을 위한 액티비티
 * 1.3초 후에 메인 액티비티로 이동 한다.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            this.startActivity(Intent(this,MainActivity::class.java))
            finish()
        },1300)
    }
}
