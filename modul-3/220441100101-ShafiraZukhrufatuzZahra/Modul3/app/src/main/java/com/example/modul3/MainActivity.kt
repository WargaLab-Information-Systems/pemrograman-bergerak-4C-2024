package com.example.modul3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.textview.MaterialTextView
import com.vinz.modul3.adapter.Adapter
import com.vinz.modul3.adapter.AdapterGrid
import com.vinz.modul3.adapter.AdapterStaggered
import com.vinz.modul3.data.Data
import com.vinz.modul3.data.DataList

class MainActivity : AppCompatActivity() {

    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var playerAdapter: Adapter
    private lateinit var playerAdapterGrid: AdapterGrid
    private lateinit var playerAdapterStaggered: AdapterStaggered
    private lateinit var playerAdapterHorizontal: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerviewHorizontal: RecyclerView
    private lateinit var btnChangeRecyclerView: ImageButton
    private var changeRV = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengambil data nama dari intent
        val getDataName = intent.getStringExtra("name")

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<MaterialTextView>(R.id.greeting_text)
        displayTitle.text = "Halo, $getDataName"

        // Menetapkan aksi ketika tombol diklik, maka akan mengubah tampilan dari RecyclerView
        btnChangeRecyclerView = findViewById(R.id.btnChangeRV)
        btnChangeRecyclerView.setOnClickListener {
            changeRV++
            if (changeRV > 2) {
                changeRV = 0
            }
            changeRecyclerView()
        }

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_player)
        recyclerviewHorizontal = findViewById(R.id.rv_player_horizontal)

        // Menginisialisasi semua adapter dengan data
        playerAdapter = Adapter(DataList.playerDataStaggeredValue)
        playerAdapterGrid = AdapterGrid(DataList.playerDataStaggeredValue)
        playerAdapterStaggered = AdapterStaggered(DataList.playerDataStaggeredValue)
        playerAdapterHorizontal = Adapter(DataList.playerDataValue)

        // Menampilkan RecyclerView
        showRecyclerList()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = playerAdapter

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = playerAdapterHorizontal

        // Menetapkan aksi ketika item di RecyclerView diklik
        playerAdapter.setOnItemClickCallback(object : Adapter.OnItemClickCallback {
            override fun onItemClicked(data: Data) {
                showSelectedPlayer(data)
            }
        })

        playerAdapterGrid.setOnItemClickCallback(object : AdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: Data) {
                showSelectedPlayer(data)
            }
        })

        playerAdapterStaggered.setOnItemClickCallback(object : AdapterStaggered.OnItemClickCallback {
            override fun onItemClicked(data: Data) {
                showSelectedPlayer(data)
            }
        })

        playerAdapterHorizontal.setOnItemClickCallback(object : Adapter.OnItemClickCallback {
            override fun onItemClicked(data: Data) {
                showSelectedPlayer(data)
            }
        })
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPlayer(data: Data) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.name)
        navigateToDetail.putExtra("description", data.description)
        navigateToDetail.putExtra("image", data.image)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }

    // Fungsi untuk mengubah tampilan RecyclerView
    private fun changeRecyclerView() {
        val rvTitle = findViewById<MaterialTextView>(R.id.rv_vertical_title)
        when (changeRV) {
            0 -> {
                rvTitle.text = "Best Hotel"
                recyclerView.layoutManager = GridLayoutManager(this,2)
                recyclerView.adapter = playerAdapterGrid
            }
            1 -> {
                rvTitle.text = "Best Hotel"
                recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.adapter = playerAdapterStaggered

            }
            2 -> {
                rvTitle.text = "Best Hotel"
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = playerAdapter
            }
        }
    }
}