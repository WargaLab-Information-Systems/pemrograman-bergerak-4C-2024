package com.example.modul6.data.remote

import retrofit2.Call
import retrofit2.http.GET

//Digunakan untuk mengambil data dari endpoint tugas
interface APIService {
    @GET("tugas")
    fun getAllPlayer() : Call<APIResponse>
}