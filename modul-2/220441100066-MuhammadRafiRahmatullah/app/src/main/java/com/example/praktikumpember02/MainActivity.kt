package com.example.praktikumpember02

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun keBeranda(view: View) {
        val intent = Intent(this, BerandaActivity::class.java)
        startActivity(intent)
    }

    fun keProfile(view: View) {}
}