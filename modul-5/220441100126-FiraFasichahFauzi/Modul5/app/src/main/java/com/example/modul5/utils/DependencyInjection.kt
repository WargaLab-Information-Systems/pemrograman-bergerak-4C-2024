package com.example.modul5.utils

import android.content.Context
import com.example.modul5.room.AppDatabase
import com.example.modul5.room.PeopleRepository

/**
 * Objek DependencyInjection berfungsi untuk menyediakan dependensi yang dibutuhkan dalam aplikasi.
 * Dalam hal ini, objek ini menyediakan AppRepository.
 *
 * Fungsi provideRepository(context: Context) digunakan untuk menyediakan instance dari AppRepository.
 * Fungsi ini pertama-tama membuat instance dari AppDatabase dan AppExecutors.
 * Kemudian, fungsi ini mendapatkan instance dari AppDao dari AppDatabase.
 * Akhirnya, fungsi ini mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors dan mengembalikannya.
 */

object DependencyInjection {
    // Menyediakan instance dari AppRepository
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

    fun providePeopleRepository(context: Context): PeopleRepository {
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