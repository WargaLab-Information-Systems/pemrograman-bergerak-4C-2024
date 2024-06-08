package com.example.modul4.utils

import android.content.Context
import com.example.modul4.room.AppDatabase
import com.example.modul4.room.PeopleRepository

object DependencyInjection {
    fun provideRepository(context: Context): PeopleRepository {
        // Membuat instance dari AppDatabase
        val database = AppDatabase.getDatabase(context)
        // Membuat instance dari AppExecutors
        val appExecutors = AppExecutors()
        // Mendapatkan instance dari AppDao dari AppDatabase
        val dao = database.PeopleDao()
        // Mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors
        return PeopleRepository.getInstance(dao, appExecutors)
        }
}