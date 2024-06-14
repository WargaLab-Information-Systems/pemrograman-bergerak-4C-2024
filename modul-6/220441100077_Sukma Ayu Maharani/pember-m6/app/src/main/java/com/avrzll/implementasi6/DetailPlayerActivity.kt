package com.avrzll.implementasi6

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.avrzll.implementasi6.data.remote.PlayerData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.imageview.ShapeableImageView

class DetailPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        val iconBackDetail = findViewById<ImageView>(R.id.backIconDetail)
        iconBackDetail.setOnClickListener {
            finish()
        }

        val getData = intent.getParcelableExtra<PlayerData>("players")!!

        val playerName = findViewById<TextView>(R.id.playerDetailName)
        val playerNumber = findViewById<TextView>(R.id.playerDetailNumber)
        val clubLogo = findViewById<ImageView>(R.id.clubDetailLogo)
        val playerImage = findViewById<ImageView>(R.id.playerDetailImage)
        val backgroundImage = findViewById<ShapeableImageView>(R.id.playerDetailBackground)
        val playerGoals = findViewById<TextView>(R.id.playerGoals)
        val playerAssists = findViewById<TextView>(R.id.playerAssists)
        val playerRatings = findViewById<TextView>(R.id.playerRatings)
        val playerPositions = findViewById<TextView>(R.id.playerPositions)
        val playerDescription = findViewById<TextView>(R.id.playerDescription)

        playerName.text = splitPlayerName(getData.name)
        playerNumber.text = "#"+getData.number.toString()
        playerGoals.text = getData.goals.toString()
        playerAssists.text = getData.assists.toString()
        playerRatings.text = getData.rating.toString()
        playerPositions.text = getData.position
        playerDescription.text = getData.description
        playerImage.loadImage(getData.player_image)
        clubLogo.loadImage(getData.club_logo)
        backgroundImage.loadImage(getData.background_card)
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    this@loadImage.setImageDrawable(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // handle cleanup here
                }
            })
    }

    private fun splitPlayerName(name: String): String {
        return if (name.contains(" ")) {
            name.replaceFirst(" ", "\n")
        } else {
            name
        }
    }
}