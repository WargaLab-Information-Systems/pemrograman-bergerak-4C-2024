package com.example.mymodul2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnmain: Button = findViewById(R.id.btnmain)
        btnmain.setOnClickListener(this)
    }
    override fun onClick(v: View?) {

        if (v != null) {
            when(v.id){
                R.id.btnmain -> {
                    val pindahIntent = Intent(this,MainActivity2::class.java)
                    startActivity(pindahIntent)
                }
            }
        }
    }
}