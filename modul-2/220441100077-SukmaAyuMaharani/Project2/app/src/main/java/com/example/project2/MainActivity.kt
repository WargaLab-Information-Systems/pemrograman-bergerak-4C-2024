package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClick: Button = findViewById(R.id.btnLanjut)
        btnClick.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

            if(v != null ) {
                when (v.id) {
                    R.id.btnLanjut -> {
                        val pindah = Intent(this, MainActivity4::class.java)
                        startActivity(pindah)
                    }
                }
            }
    }

}