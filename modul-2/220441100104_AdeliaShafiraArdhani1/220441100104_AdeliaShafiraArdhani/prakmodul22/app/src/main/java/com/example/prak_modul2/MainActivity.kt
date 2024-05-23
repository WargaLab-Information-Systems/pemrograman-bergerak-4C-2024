package com.example.prak_modul2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLanjut : Button = findViewById(R.id.button1)
        btnLanjut.setOnClickListener(this)
    }

    override fun onClick(v: View?){
        if (v != null) {
            when(v.id){
                R.id.button1 -> {
                    val pindahIntent = Intent(this, beranda::class.java)
                    startActivity(pindahIntent)
                }
            }
        }
    }
}