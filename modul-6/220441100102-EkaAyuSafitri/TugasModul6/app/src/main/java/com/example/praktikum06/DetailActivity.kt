package com.example.praktikum06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class DetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ambil data intent di dalam onCreate
        val playerName = intent.getStringExtra("name")
        val playerPosition = intent.getStringExtra("position")
        val playerDescription = intent.getStringExtra("desc")
        val playerNumber = intent.getStringExtra("number")
        val playerGoals = intent.getStringExtra("goals")
        val playerAssists = intent.getStringExtra("assists")
        val playerRating = intent.getStringExtra("rating")
        val playerClub = intent.getStringExtra("club_logo")
        val playerImage = intent.getStringExtra("player_image")
        val bgImage = intent.getStringExtra("background_card")

        val tvName = findViewById<TextView>(R.id.txt_nama_dtl)
        val tvDesc = findViewById<TextView>(R.id.txt_detail_dtl)
        val tvNumber = findViewById<TextView>(R.id.txt_number_dtl)
        val tvGoals = findViewById<TextView>(R.id.txt_goals_dtl)
        val tvAssist = findViewById<TextView>(R.id.txt_asists_dtl)
        val tvPosition = findViewById<TextView>(R.id.txt_position_dtl)
        val tvRating = findViewById<TextView>(R.id.txt_ratings_dtl)
        val imgClub = findViewById<ShapeableImageView>(R.id.img_club_dtl)
        val imgPlayer = findViewById<ImageView>(R.id.img_player_dtl)
        val imgBg = findViewById<ShapeableImageView>(R.id.img_bg_dtl)

        // Set data ke View
        tvName.text = playerName
        tvDesc.text = playerDescription
        tvNumber.text = "#$playerNumber"
        tvPosition.text = playerPosition
        tvGoals.text = playerGoals
        tvAssist.text = playerAssists
        tvRating.text = playerRating
        Glide.with(this).load(playerClub).into(imgClub)
        Glide.with(this).load(playerImage).into(imgPlayer)
        Glide.with(this).load(bgImage).into(imgBg)
    }

    fun backToMain(view: View) {
        val intent = Intent(view.context, MainActivity::class.java)
        view.context.startActivity(intent)
    }
}
