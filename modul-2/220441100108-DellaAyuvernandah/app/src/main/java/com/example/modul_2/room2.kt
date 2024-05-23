package com.example.modul_2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class room2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room2)

        val button = findViewById<ImageView>(R.id.profil1)
        button.setOnClickListener {
            val lanjut = Intent(this, room3::class.java)
            startActivity(lanjut)
        }
    }
}