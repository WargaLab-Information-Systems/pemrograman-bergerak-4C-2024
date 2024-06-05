package com.example.roomdatabase1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase1.adapter.PostAdapterRoom
import com.example.roomdatabase1.room.AppViewModel
import com.example.roomdatabase1.room.PostDatabase
import com.example.roomdatabase1.room.RoomViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
            val greetingtext = findViewById<TextView>(R.id.greeting)
            val sun = findViewById<ImageView>(R.id.sun)
            sun.setImageResource(0)
            greetingtext.setText("Good morning")
        }
        else if (jambukaapk.isBefore(noon) ){
            val greetingtext = findViewById<TextView>(R.id.greeting)
            greetingtext.setText("Good morning")
        }
        else if (jambukaapk.isAfter(noon) ){
            val greetingtext = findViewById<TextView>(R.id.greeting)
            greetingtext.setText("Good afternoon")
        }
        else if (jambukaapk.isAfter(noon2)){
            val greetingtext = findViewById<TextView>(R.id.greeting)
            val sun = findViewById<ImageView>(R.id.sun)
            sun.setImageResource(0)
            greetingtext.setText("Good night")
        }
        // Mendapatkan instance ViewModel
        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.recyclerview)

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

        val post: FloatingActionButton = findViewById(R.id.add)
        post.setOnClickListener {
            val intent = Intent(this, AddPost::class.java)
            startActivity(intent)
        }
    }
    private fun showSelectedPost(data: PostDatabase) {
    }
}