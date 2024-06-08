package com.example.tugasmodul2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton


class beranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
        val imageButton: ImageButton = findViewById(R.id.imageButton)

        imageButton.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)

            }

    }
}