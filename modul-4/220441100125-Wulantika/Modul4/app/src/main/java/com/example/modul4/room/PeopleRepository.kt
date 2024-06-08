package com.example.modul4.room

import androidx.lifecycle.LiveData
import com.example.modul4.utils.AppExecutors

class PeopleRepository private constructor(private val appDao: PeopleDao, private val appExecutors: AppExecutors) {

    // Mendapatkan semua data pemain dari database
    fun getAllPlayer(): LiveData<List<PeopleEntity>> = appDao.getAllPeople()

    // Memasukkan data pemain ke dalam database
    fun insertPlayer(player: PeopleEntity) {
        // Menjalankan operasi insert di thread yang berbeda
        appExecutors.diskIO().execute { appDao.insertPeople(player) }
    }

    companion object {
        // Variabel untuk menyimpan instance dari AppRepository
        @Volatile
        private var instance: PeopleRepository? = null

        // Mendapatkan instance dari AppRepository
        fun getInstance(
            appDao: PeopleDao,
            appExecutors: AppExecutors
        ): PeopleRepository =
            // Jika instance null, maka akan dibuat instance baru
            instance ?: synchronized(this) {
                instance ?: PeopleRepository(appDao, appExecutors)
            }.also { instance=it}
        }
}