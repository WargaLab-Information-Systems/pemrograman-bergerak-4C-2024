package com.example.modull4.room

import androidx.lifecycle.LiveData
import com.example.modull4.utils.AppExecutor

class repoSitory private constructor(private val Dao: kontenDao, private val appExecutor: AppExecutor){
    // Mendapatkan semua data pemain dari database
    fun getAllKonten(): LiveData<List<kontenDatabase>> = Dao.getAllKonten()

    // Memasukkan data pemain ke dalam database
    fun insertKonten(konten: kontenDatabase) {
        // Menjalankan operasi insert di thread yang berbeda
        appExecutor.diskIO().execute { Dao.insertKonten(konten) }
    }

    companion object {
        // Variabel untuk menyimpan instance dari AppRepository
        @Volatile
        private var instance: repoSitory? = null

        // Mendapatkan instance dari AppRepository
        fun getInstance(
            Dao: kontenDao,
            appExecutor: AppExecutor
        ): repoSitory =
            // Jika instance null, maka akan dibuat instance baru
            instance ?: synchronized(this) {
                instance ?: repoSitory(Dao, appExecutor)
            }.also { instance = it }
    }
}