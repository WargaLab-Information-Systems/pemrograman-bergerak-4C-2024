package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity4 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val btnClick: ImageView = findViewById(R.id.imgKembali)
        btnClick.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(v != null ) {
            when (v.id) {
                R.id.imgKembali -> {
                    val pindah = Intent(this, MainActivity2::class.java)
                    startActivity(pindah)
                }
            }
        }
    }

}