package com.example.perpustakaan

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class KontenBeranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_konten_beranda)

        val button = findViewById<ImageView>(R.id.Profil)
        button.setOnClickListener {
            val lanjut = Intent(this, KontenProfil::class.java)
            startActivity(lanjut)
        }
    }
}