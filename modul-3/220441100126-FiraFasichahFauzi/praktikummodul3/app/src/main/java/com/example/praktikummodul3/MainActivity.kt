package com.example.praktikummodul3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.praktikummodul3.Adapter.PlayerAdapter
import com.example.praktikummodul3.Adapter.PlayerAdapterGrid
import com.example.praktikummodul3.Data.PlayerData
import com.example.praktikummodul3.Data.PlayerDataList

class MainActivity : AppCompatActivity() {

    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var playerAdapterHorizontal: PlayerAdapter
    private lateinit var playerAdapterGrid: PlayerAdapterGrid
    private lateinit var recyclerviewHorizontal: RecyclerView
    private lateinit var recyclerViewGrid: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengambil data nama dari intent
        val getDataName = intent.getStringExtra("name")

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<TextView>(R.id.greeting_text)
        displayTitle.text = "Halo, $getDataName"

        // Menghubungkan variabel dengan komponen di layout
        recyclerviewHorizontal = findViewById(R.id.rv_player_horizontal)
        recyclerViewGrid = findViewById(R.id.rv_player)

        // Menginisialisasi semua adapter dengan data
        playerAdapterHorizontal = PlayerAdapter(PlayerDataList.PlayerDataList.playerDataGridValue)
        playerAdapterGrid = PlayerAdapterGrid(PlayerDataList.PlayerDataList.DataDummy)

        // Menampilkan RecyclerView
        showRecyclerViews()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerViews() {
        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = playerAdapterHorizontal

        // Mengatur layoutManager dan adapter untuk RecyclerView grid
        recyclerViewGrid.layoutManager = GridLayoutManager(this, 2)
        recyclerViewGrid.adapter = playerAdapterGrid

        // Menetapkan aksi ketika item di RecyclerView diklik
        playerAdapterHorizontal.setOnItemClickCallback(object : PlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PlayerData) {
                showSelectedPlayer(data)
            }
        })

        playerAdapterGrid.setOnItemClickCallback(object : PlayerAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: PlayerData) {
                showSelectedPlayer(data)
            }
        })
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPlayer(data: PlayerData) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, detail_player::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.name)
        navigateToDetail.putExtra("description", data.description)
        navigateToDetail.putExtra("image", data.image)
        navigateToDetail.putExtra("tempat", data.tempat)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }
}