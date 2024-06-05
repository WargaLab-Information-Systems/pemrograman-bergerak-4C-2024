package com.example.roomdatabase1.utils

import android.content.Context
import com.example.roomdatabase1.room.AppDatabase
import com.example.roomdatabase1.room.AppRepository

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