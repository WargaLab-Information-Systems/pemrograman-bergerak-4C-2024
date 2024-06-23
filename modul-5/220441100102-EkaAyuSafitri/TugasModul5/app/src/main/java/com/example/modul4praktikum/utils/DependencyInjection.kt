package com.example.modul4praktikum.utils

import android.content.Context
import com.example.modul4praktikum.room.AppDatabase
import com.example.modul4praktikum.room.AppRepository

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