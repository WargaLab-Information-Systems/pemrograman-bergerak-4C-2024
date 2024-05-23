package com.vinz.latihanrecyclerview1

import HorizontalAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.vinz.latihanrecyclerview1.adapter.PlayerAdapter
import com.vinz.latihanrecyclerview1.adapter.PlayerAdapterGrid
import com.vinz.latihanrecyclerview1.data.HotelData
import com.vinz.latihanrecyclerview1.data.DataList
import com.vinz.latihanrecyclerview1.data.DataTempat



class MainActivity : AppCompatActivity() {

    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var playerAdapterGrid: PlayerAdapterGrid
    private lateinit var playerAdapterHorizontal: HorizontalAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerviewHorizontal: RecyclerView
    private lateinit var btnChangeRecyclerView: Button
    private var changeRV = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengambil data nama dari intent
        val getDataName = intent.getStringExtra("name")

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<MaterialTextView>(R.id.greeting_text)
        displayTitle.text = "Halo, $getDataName"

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_player)
        recyclerviewHorizontal = findViewById(R.id.rv_player_horizontal)

        // Menginisialisasi semua adapter dengan data yang sesuai
        playerAdapter = PlayerAdapter(DataList.playerDataGridValue)
        playerAdapterGrid = PlayerAdapterGrid(DataList.playerDataGridValue)
        playerAdapterHorizontal = HorizontalAdapter(DataList.playerDataValue) // Menggunakan HorizontalAdapter

        // Menampilkan RecyclerView
        showRecyclerList()
        //changeRecyclerView
        changeRecyclerViewToGrid()
    }



    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = playerAdapter

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val horizontalAdapter = HorizontalAdapter(DataList.playerDataValue)
        recyclerviewHorizontal.adapter = horizontalAdapter // Menggunakan objek horizontalAdapter

        // Menetapkan aksi ketika item di RecyclerView diklik
        playerAdapter.setOnItemClickCallback(object : PlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: HotelData) {
                showSelectedPlayer(data)
            }
        })

        playerAdapterGrid.setOnItemClickCallback(object : PlayerAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: HotelData) {
                showSelectedPlayer(data)
            }
        })

        playerAdapterHorizontal.setOnItemClickCallback(object : HorizontalAdapter.OnItemClickCallback {


            override fun onItemClicked(data: DataTempat) {
                TODO("Not yet implemented")
            }
        })
    }


    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedPlayer(data: HotelData) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailPlayerActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.hotelName)
        navigateToDetail.putExtra("description", data.description)
        navigateToDetail.putExtra("image", data.image)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }

    // Fungsi untuk mengubah tampilan RecyclerView
    private fun changeRecyclerViewToGrid() {
        val rvTitle = findViewById<MaterialTextView>(R.id.rv_vertical_title)

        // Mengubah tampilan RecyclerView menjadi GridLayoutManager (Vertikal)
        rvTitle.text = "Best Hotel"
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = playerAdapterGrid
    }


}