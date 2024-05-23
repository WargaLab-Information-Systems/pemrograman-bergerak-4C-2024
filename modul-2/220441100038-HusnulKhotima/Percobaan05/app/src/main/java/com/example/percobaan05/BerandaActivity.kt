package com.example.percobaan05

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class BerandaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
    }

    fun toProfile(view: android.view.View) {
        val intent = Intent(this, Profile_Activity::class.java)
        startActivity(intent)
    }
}