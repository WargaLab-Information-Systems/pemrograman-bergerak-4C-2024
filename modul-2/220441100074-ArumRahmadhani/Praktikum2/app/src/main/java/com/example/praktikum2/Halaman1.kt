package com.example.praktikum2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent


class Halaman1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hal1)

        val imageButton = findViewById<ImageButton>(R.id.button1)
        imageButton.setOnClickListener {
            val intent = Intent(this, Halaman2::class.java)
            startActivity(intent)
        }
    }
}