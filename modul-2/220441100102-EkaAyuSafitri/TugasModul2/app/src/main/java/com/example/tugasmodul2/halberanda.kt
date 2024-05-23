package com.example.tugasmodul2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class halberanda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halberanda)
    }

    fun KeProfile(view : View) {
        val intent = Intent(this, halprofile::class.java)
        startActivity(intent)
    }
}