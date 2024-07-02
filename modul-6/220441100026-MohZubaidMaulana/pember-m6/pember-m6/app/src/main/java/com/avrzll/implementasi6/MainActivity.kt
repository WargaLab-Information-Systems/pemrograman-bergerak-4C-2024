package com.avrzll.implementasi6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avrzll.implementasi6.adapter.PlayerAdapter
import com.avrzll.implementasi6.data.remote.PlayerData
import com.avrzll.implementasi6.data.remote.PlayerViewModel
import com.avrzll.implementasi6.data.remote.PlayerViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class MainActivity : AppCompatActivity() {
    private lateinit var playerViewModel: PlayerViewModel
    private lateinit var adapter: PlayerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageProfile = findViewById<ShapeableImageView>(R.id.imageProfile)
        imageProfile.setOnClickListener {
            val intent = Intent(this, AuthorActivity::class.java)
            startActivity(intent)
        }

        val factory = PlayerViewModelFactory.getInstance()
        playerViewModel = ViewModelProvider(this,
            factory)[PlayerViewModel::class.java]
        recyclerView = findViewById(R.id.rvPlayer)
        recyclerView.layoutManager = LinearLayoutManager(this)
        playerViewModel.getAllPlayer()
        playerViewModel.listPlayer.observe(this) { players ->
            if (players.isNotEmpty()) {
                adapter = PlayerAdapter(players)
                recyclerView.adapter = adapter
                adapter.setOnItemClickCallback(object :
                    PlayerAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: PlayerData) {
                        showSelectedPlayer(data)
                    }
                })
            }
        }

    }

    private fun showSelectedPlayer(data: PlayerData) {
        val navigateToDetail = Intent(this, DetailPlayerActivity::class.java)
        navigateToDetail.putExtra("players", data)
        startActivity(navigateToDetail)
    }
}