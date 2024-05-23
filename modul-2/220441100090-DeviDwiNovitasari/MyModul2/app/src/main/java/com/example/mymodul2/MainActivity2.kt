package com.example.mymodul2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity2 : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val imageView33: ImageView = findViewById(R.id.imageView33)
        imageView33.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.imageView33 -> {
                    val pindahIntent = Intent(this,MainActivity3::class.java)
                    startActivity(pindahIntent)
                }
            }
        }
    }
}