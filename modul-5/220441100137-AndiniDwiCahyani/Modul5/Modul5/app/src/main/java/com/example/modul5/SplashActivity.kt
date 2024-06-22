package com.example.modul5


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splash_activity)

        Handler(Looper.getMainLooper()).postDelayed({
            goToMainActivity()
        },300L)
    }

    private fun goToMainActivity(){
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}