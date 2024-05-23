package com.example.modultiga


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modultiga.adapter.HotelAdapter
import com.example.modultiga.adapter.LocationAdapter
import com.example.modultiga.data.DestinasiList
import com.example.modultiga.data.HotelData
import com.example.modultiga.data.LocationData
import com.google.android.material.textview.MaterialTextView

class BerandaActivity : AppCompatActivity() {
    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var hotelAdapter: HotelAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerviewHorizontal: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        // Mengambil data nama dari intent
        val getDataName = intent.getStringExtra("Nama")

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<MaterialTextView>(R.id.materialTextView)
        displayTitle.text = "Hi, $getDataName"


        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.grid)
        recyclerviewHorizontal = findViewById(R.id.recyclerView)

        // Menginisialisasi semua adapter dengan data
        locationAdapter = LocationAdapter(DestinasiList.LokasiDum)
        hotelAdapter = HotelAdapter(DestinasiList.HotelDum)


        // Menampilkan RecyclerView
        showRecyclerList()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = hotelAdapter

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = locationAdapter

        // Menetapkan aksi ketika item di RecyclerView diklik
        locationAdapter.setOnItemClickCallback(object : LocationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LocationData) {
                showSelectedLocation(data)
            }
        })

        hotelAdapter.setOnItemClickCallback(object : HotelAdapter.OnItemClickCallback {
            override fun onItemClicked(data: HotelData) {
                showSelectedHotel(data)
            }
        })

    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedLocation(data: LocationData) {
//         Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.name)
        navigateToDetail.putExtra("lokasi", data.lokasi)
        navigateToDetail.putExtra("image", data.image)
        navigateToDetail.putExtra("description", data.description)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }

    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedHotel(data: HotelData) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.nama)
        navigateToDetail.putExtra("description", data.deskripsi)
        navigateToDetail.putExtra("image", data.gambar)


        // Memulai activity baru
        startActivity(navigateToDetail)
    }

}