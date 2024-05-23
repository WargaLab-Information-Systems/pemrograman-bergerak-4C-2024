package com.example.prak_modul2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView


class beranda : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val jdArt : TextView = findViewById(R.id.jd2)
        jdArt.setOnClickListener(this)
    }

    override fun onClick(v: View?){
        if (v != null) {
            when(v.id){
                R.id.jd2 -> {
                    val pindahIntent = Intent(this, artikel::class.java)
                    startActivity(pindahIntent)
                }
            }
        }
    }
}