package com.example.latihanpraktikum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class hal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hal2)

        val imageView = findViewById<ImageView>(R.id.utm)
        imageView.setOnClickListener {
            // Intent untuk membuka HalamanKetigaActivity
            val intent = Intent(this, hall::class.java)
            startActivity(intent)
        }
    }
}
