package com.example.perpustakaan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<MaterialButton>(R.id.Button)
        button.setOnClickListener {
            val lanjut = Intent(this, KontenBeranda::class.java)
            startActivity(lanjut)
        }
    }
}