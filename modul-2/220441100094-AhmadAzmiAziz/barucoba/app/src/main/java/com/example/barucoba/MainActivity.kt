package com.example.barucoba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraintView = findViewById<Button>(R.id.button)
        constraintView.setOnClickListener {
            val intent = Intent(this, Activity2MainActivity::class.java)
            startActivity(intent)
        }
    }
}
