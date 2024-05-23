package com.example.tugasmodul2praktikum

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class Beranda : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
        val terus: ImageButton = findViewById(R.id.utmbtn)
        terus.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.utmbtn -> {
                    val nextIntent = Intent(this, utm::class.java)
                    startActivity(nextIntent)
                }
            }
        }
    }
}