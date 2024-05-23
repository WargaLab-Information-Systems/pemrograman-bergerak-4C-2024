package com.example.tugasmodul2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editTextText)

        editText.setOnClickListener {
            editText.text.clear()
        }
    }

    fun beranda(view: View) {
        // Intent untuk memulai aktivitas baru
        val intent = Intent(this, HalamanBeranda::class.java)
        startActivity(intent)
    }

}