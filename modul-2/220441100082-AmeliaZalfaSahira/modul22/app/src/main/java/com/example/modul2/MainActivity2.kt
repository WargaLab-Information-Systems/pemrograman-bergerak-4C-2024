package com.example.modul2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val button = findViewById<ImageView>(R.id.Profil)
        button.setOnClickListener {
            val lanjut = Intent(this, MainActivity3::class.java)
            startActivity(lanjut)
        }
    }
}