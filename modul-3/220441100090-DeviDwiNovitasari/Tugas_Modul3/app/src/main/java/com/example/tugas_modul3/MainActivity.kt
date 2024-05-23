package com.example.tugas_modul3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.textview.MaterialTextView
import com.example.tugas_modul3.adapter.PlaceAdapter
import com.example.tugas_modul3.adapter.PlaceAdapterGrid
import com.example.tugas_modul3.adapter.PlaceAdapterStaggered
import com.example.tugas_modul3.data.PlaceData
import com.example.tugas_modul3.data.PlaceDataList

class MainActivity : AppCompatActivity() {
    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var placeAdapter: PlaceAdapter
    private lateinit var placeAdapterGrid: PlaceAdapterGrid
    private lateinit var placeAdapterStaggered: PlaceAdapterStaggered
    private lateinit var placeAdapterHorizontal: PlaceAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerviewHorizontal: RecyclerView
    private lateinit var btnChangeRecyclerView: TextView
    private var changeRV = 0

    @SuppressLint("MissingInflatedId")
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
        recyclerView = findViewById(R.id.rv_place)
        recyclerviewHorizontal = findViewById(R.id.rv_place_horizontal)

        // Menginisialisasi semua adapter dengan data
        placeAdapter = PlaceAdapter(PlaceDataList.playerDataStaggeredValue)
        placeAdapterGrid = PlaceAdapterGrid(PlaceDataList.playerDataStaggeredValue)
        placeAdapterStaggered = PlaceAdapterStaggered(PlaceDataList.playerDataStaggeredValue)
        placeAdapterHorizontal = PlaceAdapter(PlaceDataList.placeDataValue)

        // Menampilkan RecyclerView
        showRecyclerList()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = placeAdapterGrid

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = placeAdapterHorizontal

        // Menetapkan aksi ketika item di RecyclerView diklik
        placeAdapter.setOnItemClickCallback(object : PlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PlaceData) {
                showSelectedPlayer(data)
            }
        })

        placeAdapterGrid.setOnItemClickCallback(object : PlaceAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: PlaceData) {
                showSelectedPlayer(data)
            }
        })

        placeAdapterStaggered.setOnItemClickCallback(object : PlaceAdapterStaggered.OnItemClickCallback {
            override fun onItemClicked(data: PlaceData) {
                showSelectedPlayer(data)
            }
        })

        placeAdapterHorizontal.setOnItemClickCallback(object : PlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PlaceData) {
                showSelectedPlayer(data)
            }
        })
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPlayer(data: PlaceData) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailPlaceActivity::class.java)

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
                // Mengubah tampilan RecyclerView menjadi LinearLayoutManager (Vertikal)
                rvTitle.text = "LinearLayoutManager (Vertikal)"
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = placeAdapter
            }
            1 -> {
                // Mengubah tampilan RecyclerView menjadi GridLayoutManager (Vertikal)
                rvTitle.text = "GridLayoutManager (Vertikal)"
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                recyclerView.adapter = placeAdapterGrid
            }
            2 -> {
                // Mengubah tampilan RecyclerView menjadi StaggeredLayoutManager (Vertikal)
                rvTitle.text = "StaggeredLayoutManager (Vertikal)"
                recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.adapter = placeAdapterStaggered
            }
        }
    }
}