package com.example.praktikum2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView

class Halaman2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hal2)

        val imageView = findViewById<ImageView>(R.id.utm1)
        imageView.setOnClickListener {
            val intent = Intent(this, Halaman3::class.java)
            startActivity(intent)
        }
    }
}