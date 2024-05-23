package com.example.myapplication3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication3.adapter.WisataAdapter
import com.example.myapplication3.adapter.WisataAdapterGrid
import com.example.myapplication3.adapter.WisataAdapterStaggered
import com.example.myapplication3.data.WisataData
import com.example.myapplication3.data.WisataDataList
import com.google.android.material.textview.MaterialTextView


class MainActivity : AppCompatActivity() {

    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var wisataAdapter: WisataAdapter
    private lateinit var wisataAdapterGrid: WisataAdapterGrid
    private lateinit var wisataAdapterStaggered: WisataAdapterStaggered
    private lateinit var wisataAdapterHorizontal: WisataAdapter
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
        recyclerView = findViewById(R.id.rv_player)
        recyclerviewHorizontal = findViewById(R.id.rv_wisata_horizontal)

        // Menginisialisasi semua adapter dengan data
        wisataAdapter = WisataAdapter(WisataDataList.wisataDataStaggeredValues)
        wisataAdapterGrid = WisataAdapterGrid(WisataDataList.wisataDataStaggeredValues)
        wisataAdapterStaggered = WisataAdapterStaggered(WisataDataList.wisataDataStaggeredValues)
        wisataAdapterHorizontal = WisataAdapter(WisataDataList.wisataDataValues)

        // Menampilkan RecyclerView
        showRecyclerList()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = wisataAdapterGrid

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = wisataAdapterHorizontal

        // Menetapkan aksi ketika item di RecyclerView diklik
        wisataAdapter.setOnItemClickCallback(object : WisataAdapter.OnItemClickCallback {
            override fun onItemClicked(data: WisataData) {
                showSelectedPlayer(data)
            }
        })

        wisataAdapterGrid.setOnItemClickCallback(object : WisataAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: WisataData) {
                showSelectedPlayer(data)
            }
        })

        wisataAdapterStaggered.setOnItemClickCallback(object : WisataAdapterStaggered.OnItemClickCallback {
            override fun onItemClicked(data: WisataData) {
                showSelectedPlayer(data)
            }
        })

        wisataAdapterHorizontal.setOnItemClickCallback(object : WisataAdapter.OnItemClickCallback {
            override fun onItemClicked(data: WisataData) {
                showSelectedPlayer(data)
            }
        })
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPlayer(data: WisataData) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailWisataActivity::class.java)

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
                rvTitle.text = "Linear"
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = wisataAdapter
            }

            1 -> {
                // Mengubah tampilan RecyclerView menjadi GridLayoutManager (Vertikal)
                rvTitle.text = "Grid"
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                recyclerView.adapter = wisataAdapterGrid
            }

            2 -> {
                // Mengubah tampilan RecyclerView menjadi StaggeredLayoutManager (Vertikal)
                rvTitle.text = "Staggered"
                recyclerView.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.adapter = wisataAdapterStaggered
            }
        }}}

