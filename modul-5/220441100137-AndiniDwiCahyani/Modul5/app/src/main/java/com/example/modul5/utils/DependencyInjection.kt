package com.example.modul5.utils

import android.content.Context
import com.example.modul5.room.AppDatabase
import com.example.modul5.room.AppRepository

//Membuat objek yang dibutuhkan oleh kelas yang  bisa digunakan di semua komponen
object DependencyInjection {
    fun provideRepository(context: Context): AppRepository {
        // Membuat instance dari AppDatabase
        val database = AppDatabase.getDatabase(context)
        // Membuat instance dari AppExecutors
        val appExecutors = AppExecutors()
        // Mendapatkan instance dari AppDao dari AppDatabase
        val dao = database.appDao()
        // Mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors
        return AppRepository.getInstance(dao, appExecutors)
    }
}