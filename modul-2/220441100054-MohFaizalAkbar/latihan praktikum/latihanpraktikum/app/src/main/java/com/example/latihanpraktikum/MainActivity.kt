package com.example.latihanpraktikum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageButton = findViewById<ImageButton>(R.id.bt)
        imageButton.setOnClickListener {
            // Intent untuk membuka HalamanKeduaActivity
            val intent = Intent(this, hal::class.java)
            startActivity(intent)
        }
    }
}
