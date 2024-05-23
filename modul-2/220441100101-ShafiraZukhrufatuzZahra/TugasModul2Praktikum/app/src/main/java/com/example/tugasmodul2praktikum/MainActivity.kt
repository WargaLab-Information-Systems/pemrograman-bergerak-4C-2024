package com.example.tugasmodul2praktikum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selanjutnya: Button = findViewById(R.id.lanjut)
        selanjutnya.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.lanjut -> {
                    val pindahIntent = Intent(this, Beranda::class.java)
                    startActivity(pindahIntent)
                }
            }
        }
    }
}
