package com.example.barucoba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.content.Intent

class Activity2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_main)

        val imageView = findViewById<ImageView>(R.id.utm)
        imageView.setOnClickListener {
            // Intent untuk membuka HalamanKetigaActivity
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
            }
        }
}