package com.example.tugasmodul6

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmodul6.adapter.PracticeAdapter
import com.example.tugasmodul6.data.Player
import com.example.tugasmodul6.data.PracticeViewModel
import com.example.tugasmodul6.data.PracticeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var practiceViewModel: PracticeViewModel
    private lateinit var adapter: PracticeAdapter
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = PracticeViewModelFactory.getInstance()
        practiceViewModel = ViewModelProvider(this, factory)[PracticeViewModel::class.java]

        recyclerView = findViewById(R.id.rv_retrofit)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PracticeAdapter(emptyList()) // Initialize with empty list
        recyclerView.adapter = adapter

        practiceViewModel.getAllPlayer()
        practiceViewModel.listPlayer.observe(this) { players ->
            if (players.isNotEmpty()) {
                adapter = PracticeAdapter(players)
                recyclerView.adapter = adapter
                adapter.setOnItemClickCallback(object : PracticeAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Player) {
                        showSelectedPlayer(data)
                    }
                })
            }
        }

        // Set click listener for profile button using findViewById and onClickListener
        val profil = findViewById<ImageView>(R.id.profil)
        profil.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, AboutCreatorActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun showSelectedPlayer(data: Player) {
        val navigateToDetail = Intent(this, DetailRetrofitActivity::class.java)
        navigateToDetail.putExtra("player", data)
        startActivity(navigateToDetail)
    }
}