package com.example.modul2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modul2.R.id.button
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<MaterialButton>(button)
        button.setOnClickListener {
            val lanjut = Intent(this, MainActivity2::class.java)
            startActivity(lanjut)
        }
    }
}