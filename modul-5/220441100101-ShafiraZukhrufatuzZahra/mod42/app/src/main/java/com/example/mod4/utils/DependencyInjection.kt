package com.example.mod4.utils

import android.content.Context
import com.example.mod4.room.AppDatabase
import com.example.mod4.room.PostRepository

object DependencyInjection {
    fun provideRepository(context: Context): PostRepository {
        // Membuat instance dari AppDatabase
        val database = AppDatabase.getDatabase(context)
        // Membuat instance dari AppExecutors
        val appExecutors = AppExecutors()
        // Mendapatkan instance dari AppDao dari AppDatabase
        val dao = database.PostDao()
        // Mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors
        return PostRepository.getInstance(dao, appExecutors)
    }
}