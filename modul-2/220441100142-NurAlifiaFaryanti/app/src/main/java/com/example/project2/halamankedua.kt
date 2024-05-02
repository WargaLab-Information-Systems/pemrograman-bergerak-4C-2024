package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class halamankedua : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halamankedua)

        val btnClick: ImageView = findViewById(R.id.imgKembali)
        btnClick.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(v != null ) {
            when (v.id) {
                R.id.imgKembali -> {
                    val pindah = Intent(this, halamanketiga::class.java)
                    startActivity(pindah)
                }
            }
        }
    }

}