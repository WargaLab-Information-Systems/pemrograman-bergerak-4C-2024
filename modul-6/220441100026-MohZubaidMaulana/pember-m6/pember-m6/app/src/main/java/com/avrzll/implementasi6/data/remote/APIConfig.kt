package com.avrzll.implementasi6.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIConfig {
    companion object{
        // Fungsi untuk mendapatkan layanan API
        fun getApiService(): APIService {
            // Membuat interceptor untuk logging HTTP. Level BODY berarti kita akan log detail request dan response.
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            // Membuat client HTTP dan menambahkan interceptor logging ke dalamnya
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            // Membuat instance Retrofit
            val retrofit = Retrofit.Builder()
                // Menentukan base URL untuk request API
                .baseUrl("https://praktikum-1-d0606905.deta.app/")
                // Menambahkan converter factory untuk mengubah response menjadi objek Gson
                .addConverterFactory(GsonConverterFactory.create())
                // Menentukan client HTTP untuk Retrofit
                .client(client)
                .build()
            // Mengembalikan layanan API yang telah dibuat oleh Retrofit
            return retrofit.create(APIService::class.java)
        }
    }
}