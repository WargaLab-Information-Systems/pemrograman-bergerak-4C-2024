package com.example.praktikum2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class hal2 : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hal2)

        val klikgambar: ImageView = findViewById(R.id.klikgambar)
        klikgambar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.klikgambar -> {
                    val lanjut = Intent(this,hal3::class.java)
                    startActivity(lanjut)
                }
            }
        }
    }
}

