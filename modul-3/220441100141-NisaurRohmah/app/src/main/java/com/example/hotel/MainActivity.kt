package com.example.hotel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.adapter.HotelAdapter
import com.example.hotel.adapter.HotelAdapterGrid
import com.example.hotel.data.HotelData
import com.example.hotel.data.HotelDataList

import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var hotelAdapter: HotelAdapter
    private lateinit var hotelAdapterGrid: HotelAdapterGrid
    private lateinit var hotelAdapterHorizontal: HotelAdapter
    private lateinit var recyclerviewHorizontal: RecyclerView
    private lateinit var getDataName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengambil data nama dari intent
        getDataName = intent.getStringExtra("name") ?: ""

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<TextView>(R.id.greeting_text)
        displayTitle.text = "Halo, $getDataName"

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_vertical)
        recyclerviewHorizontal = findViewById(R.id.rv_horizontal)

        /// Menginisialisasi semua adapter dengan data
        hotelAdapterGrid = HotelAdapterGrid(HotelDataList.hotelDataValue)
        hotelAdapterHorizontal = HotelAdapter(HotelDataList.hotelDataValue)

        // Menampilkan RecyclerView
        showRecyclerList()
    }

    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = hotelAdapterGrid

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = hotelAdapterHorizontal

        hotelAdapterGrid.setOnItemClickCallback(object : HotelAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: HotelData) {
                showSelectedHotel(data)
            }
        })

        hotelAdapterHorizontal.setOnItemClickCallback(object : HotelAdapter.OnItemClickCallback {
            override fun onItemClicked(data: HotelData) {
                showSelectedHotel(data)
            }
        })
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
        private fun showSelectedHotel(data: HotelData) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, detail_hotel::class.java)
        val displayTitle = findViewById<TextView>(R.id.hotel_name)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", getDataName)
        navigateToDetail.putExtra("hotelTitle", data.name)
        navigateToDetail.putExtra("description", data.description)
        navigateToDetail.putExtra("image", data.image)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }


}