package com.example.praktikum2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Halaman3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hal3)

        val imageView = findViewById<ImageView>(R.id.utm1)
        imageView.setOnClickListener {
            val intent = Intent(this, Halaman1::class.java)
            startActivity(intent)
        }
    }
}