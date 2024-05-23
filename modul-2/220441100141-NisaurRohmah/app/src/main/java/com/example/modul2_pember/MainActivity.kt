package com.example.modul2_pember

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.modul2_pember.R.id.logoutm

class MainActivity : AppCompatActivity(), View.OnClickListener {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 : Button = findViewById(R.id.button1);
        button1.setOnClickListener(this)
        }


    override fun onClick(v: View?) {
         if (v != null ) {
             when(v.id) {
                 R.id.button1 -> {
                     val pindahIntent = Intent(this, beranda::class.java)


                     startActivity(pindahIntent)
                 }
             }
    }
}
}