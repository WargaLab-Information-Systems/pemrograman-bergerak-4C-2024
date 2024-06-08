package com.example.modul4

import com.example.modul4.adapter.PeopleAdapterRoom
import com.example.modul4.room.PeopleEntity
import com.example.modul4.room.PeopleViewModel
import com.example.modul4.room.PeopleViewModelFactory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // Mendeklarasikan ViewModel untuk interaksi dengan database
    private lateinit var appViewModel: PeopleViewModel
    // Mendeklarasikan adapter untuk RecyclerView
    private lateinit var playerAdapterRoom: PeopleAdapterRoom
    // Mendeklarasikan RecyclerView untuk menampilkan daftar pemain
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mendapatkan instance ViewModel
        val factory = PeopleViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[PeopleViewModel::class.java]

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_player_room)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Mengamati perubahan data pemain dan memperbarui RecyclerView
        appViewModel.getAllPlayer().observe(this) { playerData ->
            if (playerData != null) {
                playerAdapterRoom = PeopleAdapterRoom(playerData)
                recyclerView.adapter = playerAdapterRoom

                // Menangani aksi klik pada item di RecyclerView
                playerAdapterRoom.setOnItemClickCallback(object :
                    PeopleAdapterRoom.OnItemClickCallback {
                    override fun onItemClicked(data: PeopleEntity) {
                        showSelectedPlayer(data)
                    }

                    override fun onItemMore(data: PeopleEntity) {
                        PopUpPractice(data).show(supportFragmentManager, PopUpPractice.TAG)
                    }
                })
            }
        }

        // Menangani aksi klik pada tombol tambah pemain
        val btnAdd = findViewById<FloatingActionButton>(R.id.btn_add_player)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddRoomActivity::class.java)
            startActivity(intent)
        }
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPlayer(data: PeopleEntity) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailPeopleActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("player", data)

        // Selalu menyertakan nama pemain dalam intent
        navigateToDetail.putExtra("name", data.name)
        navigateToDetail.putExtra("description", data.description)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }

    // Fungsi yang dipanggil ketika activity di-restart
    override fun onRestart() {
        super.onRestart()

        // Memperbarui daftar pemain
        appViewModel.getAllPlayer()
        }
}