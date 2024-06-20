package com.example.modul2projek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class LinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout)
        val imageView = findViewById<ImageView>(R.id.logoutm)
        imageView.setOnClickListener {
            // Intent untuk membuka HalamanKetigaActivity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}