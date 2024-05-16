package com.example.modul2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btnClick: ImageView = findViewById(R.id.imageView1)
        btnClick.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(v != null ) {
            when (v.id) {
                R.id.imageView1 -> {
                    val pindah = Intent(this, MainActivity3::class.java)
                    startActivity(pindah)
                }
            }
        }
    }

}