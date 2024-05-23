package com.example.modul2_pember

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class beranda : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val logoutm : ImageView = findViewById(R.id.logoutm);
        logoutm.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null ) {
            when(v.id) {
                R.id.logoutm -> {
                    val pindahIntent = Intent(this, profile::class.java)


                    startActivity(pindahIntent)
                }
            }
        }
    }
}