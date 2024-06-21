package com.example.praktikum06.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("position") val position: String,
    @SerializedName("goals") val goals: String,
    @SerializedName("assists") val assists: String,
    @SerializedName("ga") val ga: String,
    @SerializedName("number") val number: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("club_logo") val clubLogo: String,
    @SerializedName("player_image") val playerImage: String,
    @SerializedName("background_card") val backgroundCard: String
)

data class PlayerResponse(
    @SerializedName("data") val data: List<Player>
)
