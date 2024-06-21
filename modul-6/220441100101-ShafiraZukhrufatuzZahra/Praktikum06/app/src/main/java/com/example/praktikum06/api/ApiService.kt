package com.example.praktikum06.api

import retrofit2.Call
import retrofit2.http.GET
import com.example.praktikum06.model.PlayerResponse

interface ApiService {
    @GET("tugas")
    fun getPlayers(): Call<PlayerResponse>
}
