package com.example.modul4praktikum.room

import androidx.lifecycle.LiveData
import com.example.modul4praktikum.utils.AppExecutors

class AppRepository private constructor(private val appDao: AppDao, private val appExecutors: AppExecutors) {

    // Mendapatkan semua data pemain dari database
    fun getAllPost(): LiveData<List<PostDatabase>> = appDao.getAllPost()

    // Memasukkan data pemain ke dalam database
    fun insertPost(post: PostDatabase) {
        // Menjalankan operasi insert di thread yang berbeda
        appExecutors.diskIO().execute { appDao.insertPost(post) }
    }

    // Menghapus data post dari database
    fun deletePost(post: PostDatabase) {
        // Menjalankan operasi delete di thread yang berbeda
        appExecutors.diskIO().execute { appDao.deletePost(post) }
    }
    // Memperbarui data post dalam database
    fun updatePost(post: PostDatabase) {
        // Menjalankan operasi update di thread yang berbeda
        appExecutors.diskIO().execute { appDao.updatePost(post) }
    }

    companion object {
        // Variabel untuk menyimpan instance dari AppRepository
        @Volatile
        private var instance: AppRepository? = null

        // Mendapatkan instance dari AppRepository
        fun getInstance(
            appDao: AppDao,
            appExecutors: AppExecutors
        ): AppRepository =
            // Jika instance null, maka akan dibuat instance baru
            instance ?: synchronized(this) {
                instance ?: AppRepository(appDao, appExecutors)
            }.also { instance = it }
    }
}