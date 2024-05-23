package com.example.praktikum03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum03.adapter.HotelAdapter
import com.example.praktikum03.adapter.HotelAdapterGrid
import com.example.praktikum03.data.HotelData
import com.example.praktikum03.data.HotelDataList
import com.google.android.material.textview.MaterialTextView

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var hotelAdapterGrid: HotelAdapterGrid
    private lateinit var hotelAdapterHorizontal: HotelAdapter
    private lateinit var recyclerviewHorizontal: RecyclerView
    private lateinit var getDataName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Mengambil data nama dari intent
        getDataName = intent.getStringExtra("name") ?: ""

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<MaterialTextView>(R.id.greeting_text)
        displayTitle.text = "Halo, $getDataName"

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_player)
        recyclerviewHorizontal = findViewById(R.id.rv_player_horizontal)

        // Menginisialisasi semua adapter dengan data
        hotelAdapterGrid = HotelAdapterGrid(HotelDataList.HotelDataValue)
        hotelAdapterHorizontal = HotelAdapter(HotelDataList.HotelDataValue)

        showRecyclerList()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = hotelAdapterGrid

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = hotelAdapterHorizontal

        hotelAdapterGrid.setOnItemClickCallback(object : HotelAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: HotelData) {
                showSelectedPlayer(data)
            }
        })
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPlayer(data: HotelData) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailActivity::class.java)
        val displayTitle = findViewById<MaterialTextView>(R.id.greeting_text)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", getDataName)
        navigateToDetail.putExtra("hotelname", data.name)
        navigateToDetail.putExtra("description", data.description)
        navigateToDetail.putExtra("image", data.image)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }


}