package com.example.modull4.utils

import android.content.Context
import com.example.modull4.room.dataBase
import com.example.modull4.room.repoSitory

object DepedencyInjection {
    fun provideRepository(context: Context): repoSitory {
        // Membuat instance dari AppDatabase
        val database = dataBase.getDatabase(context)
        // Membuat instance dari AppExecutors
        val appExecutors = AppExecutor()
        // Mendapatkan instance dari AppDao dari AppDatabase
        val dao = database.kontenDao()
        // Mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors
        return repoSitory.getInstance(dao, appExecutors)
    }
}