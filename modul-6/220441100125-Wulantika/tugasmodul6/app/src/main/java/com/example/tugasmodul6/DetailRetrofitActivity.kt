package com.example.tugasmodul6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tugasmodul6.data.Player
import com.google.android.material.imageview.ShapeableImageView

class DetailRetrofitActivity : AppCompatActivity() {
    // Fungsi ini dipanggil saat activity dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur layout untuk activity ini
        setContentView(R.layout.activity_detail_retrofit)

        // Mendapatkan data makanan dari intent
        val getData = intent.getParcelableExtra<Player>("player")!!

        // Menghubungkan variabel dengan komponen di layout
        val playerName = findViewById<TextView>(R.id.name)
        val playerNumber = findViewById<TextView>(R.id.number)
        val playerGoals = findViewById<TextView>(R.id.goals)
        val playerAsissts = findViewById<TextView>(R.id.asissts)
        val playerRating = findViewById<TextView>(R.id.rating)
        val playerPosition = findViewById<TextView>(R.id.position)
        val playerDesc = findViewById<TextView>(R.id.player_description)
        val playerImage = findViewById<ImageView>(R.id.player_image)
        val logoImage = findViewById<ImageView>(R.id.club_logo)
        val backgroundImage = findViewById<ShapeableImageView>(R.id.background_image)

        // Mengatur teks untuk nama dan deskripsi makanan
        playerName.text = getData.name
        playerNumber.text = getData.number.toString()
        playerGoals.text = getData.goals.toString()
        playerAsissts.text = getData.assists.toString()
        playerRating.text = getData.rating.toString()
        playerPosition.text = getData.position
        playerDesc.text = getData.description
        // Menggunakan library Glide untuk memuat gambar dari URL dan menampilkannya di ImageView
        Glide.with(this)
            .load(getData.player_image)
            .into(playerImage)

        Glide.with(this)
            .load(getData.club_logo)
            .into(logoImage)

        Glide.with(this)
            .load(getData.background_card)
            .into(backgroundImage)
    }
}