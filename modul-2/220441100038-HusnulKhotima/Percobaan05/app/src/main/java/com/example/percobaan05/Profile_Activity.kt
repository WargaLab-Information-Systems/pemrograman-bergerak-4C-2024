package com.example.percobaan05

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

class Profile_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
    fun toMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}