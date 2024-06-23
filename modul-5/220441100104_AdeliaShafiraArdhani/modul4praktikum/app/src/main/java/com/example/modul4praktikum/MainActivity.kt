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
import com.example.modul4praktikum.Activity.PopUpFragment
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
        val earlyMorning = LocalTime.of(5, 0)
        val noon = LocalTime.of(12, 0)
        val evening = LocalTime.of(18, 0)
        val night = LocalTime.of(23, 59) // Representing midnight

        val greetingtext = findViewById<TextView>(R.id.greetingtext)
        val sun = findViewById<ImageView>(R.id.sun)

        when {
            jambukaapk.isBefore(earlyMorning) -> {
                sun.setImageResource(0)
                greetingtext.text = "Selamat Subuh"
            }
            jambukaapk.isBefore(noon) -> {
                greetingtext.text = "Selamat Pagi"
            }
            jambukaapk.isBefore(evening) -> {
                greetingtext.text = "Selamat Siang"
            }
            jambukaapk.isBefore(night) -> {
                sun.setImageResource(R.drawable.moon)
                greetingtext.text = "Selamat Malam "
            }
            else -> {
                // Optional: handle the case for exactly midnight if needed
                greetingtext.text = "Selamat Malam"
            }
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

                    override fun onMoreClicked(data: PostDatabase, position: Int) {
                        PopUpFragment(data, position).show(supportFragmentManager, PopUpFragment.TAG)


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
        // Membuat intent untuk berpindah ke DetailPlayerActivity
//        val navigateToDetail = Intent(this, DetailPlayerActivity::class.java)
//
//        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
//        navigateToDetail.putExtra("player", data)

        // Memulai activity baru
//        startActivity(navigateToDetail)
    }
}