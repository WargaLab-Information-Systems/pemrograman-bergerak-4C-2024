package com.example.modull4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.modull4.adapter.kontenAdapter
import com.example.modull4.room.kontenViewModel
import com.example.modull4.room.kontenViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var appViewModel: kontenViewModel

    private lateinit var kontenAdapter: kontenAdapter

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = kontenViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[kontenViewModel::class.java]

        recyclerView = findViewById(R.id.recycler_konten)
//        recyclerView.layoutManager = StaggeredGridLayoutManager(this)

        appViewModel.getAllKonten().observe(this) {kontenData ->
            if (kontenData != null){
                kontenAdapter = kontenAdapter(kontenData)
                recyclerView.adapter = kontenAdapter

//                kontenAdapter.setOnItemClickCallback()
//                kontenAdapter.setOnItemClickCallback(object :
//                    kontenAdapter.OnItemClickCallback{
//                        override fun onItemClicked(data: kontenDatabase) {
//                            showSelectedKonten(data)
//                        }
//                    }
//                )

            }
        }

        val btnAdd = findViewById<FloatingActionButton>(R.id.addButton)
        btnAdd.setOnClickListener{
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }


    }

//    private fun showSelectedPlayer(data: kontenDatabase) {
//        // Membuat intent untuk berpindah ke DetailPlayerActivity
//        val navigateToDetail = Intent(this, DetailPlayerActivity::class.java)
//
//        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
//        navigateToDetail.putExtra("player", data)
//
//        // Memulai activity baru
//        startActivity(navigateToDetail)
//    }




    override fun onRestart() {
        super.onRestart()

        // Memperbarui daftar pemain
        appViewModel.getAllKonten()
    }
}