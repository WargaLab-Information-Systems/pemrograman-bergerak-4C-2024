package com.example.tugasmodul2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton


class HalamanBeranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_beranda)

        val imageButton: ImageButton = findViewById(R.id.imageButton1)

        imageButton.setOnClickListener {
            val intent = Intent(this, HalamanProfil::class.java)
            startActivity(intent)

        }
    }
}