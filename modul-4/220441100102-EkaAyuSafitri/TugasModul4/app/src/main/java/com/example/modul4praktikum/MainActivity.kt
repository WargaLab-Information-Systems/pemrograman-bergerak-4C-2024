package com.example.modul4praktikum

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4praktikum.adapter.PostAdapterRoom
import com.example.modul4praktikum.room.AppViewModel
import com.example.modul4praktikum.room.PostDatabase
import com.example.modul4praktikum.room.RoomViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.modul4praktikum.R
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private lateinit var appViewModel: AppViewModel
    // Mendeklarasikan adapter untuk RecyclerView
    private lateinit var playerAdapterRoom: PostAdapterRoom

    // Mendeklarasikan RecyclerView untuk menampilkan daftar pemain
    private lateinit var recyclerView: RecyclerView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jambukaapk = LocalTime.now()
        val noon0 = LocalTime.of(5, 0)
        val noon = LocalTime.of(12, 0)
        val noon2 = LocalTime.of(18, 0)
        if(jambukaapk.isBefore(noon0)){
            val greetingtext = findViewById<TextView>(R.id.greetingtext)
            val sun = findViewById<ImageView>(R.id.sun)
            sun.setImageResource(0)
            greetingtext.setText("Selamat Subuh")
        }
        else if (jambukaapk.isBefore(noon) ){
            val greetingtext = findViewById<TextView>(R.id.greetingtext)
            greetingtext.setText("Selamat Pagi")
        }
        else if (jambukaapk.isAfter(noon) ){
            val greetingtext = findViewById<TextView>(R.id.greetingtext)
            greetingtext.setText("Selamat Siang")
        }
        else if (jambukaapk.isAfter(noon2)){
            val greetingtext = findViewById<TextView>(R.id.greetingtext)
            val sun = findViewById<ImageView>(R.id.sun)
            sun.setImageResource(0)
            greetingtext.setText("Selamat Malam")
        }
        // Mendapatkan instance ViewModel
        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_post)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Mengamati perubahan data pemain dan memperbarui RecyclerView
        appViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                playerAdapterRoom = PostAdapterRoom(postData)
                recyclerView.adapter = playerAdapterRoom

                // Menangani aksi klik pada item di RecyclerView
                playerAdapterRoom.setOnItemClickCallback(object : PostAdapterRoom.OnItemClickCallback {
                    override fun onItemClicked(data: PostDatabase) {
                        showSelectedPost(data)
                    }
                })
            }
        }

        val post: FloatingActionButton = findViewById(R.id.fab)
        post.setOnClickListener {
            val intent = Intent(this, AddPost::class.java)
            startActivity(intent)
        }
    }
    private fun showSelectedPost(data: PostDatabase) {
    }
}