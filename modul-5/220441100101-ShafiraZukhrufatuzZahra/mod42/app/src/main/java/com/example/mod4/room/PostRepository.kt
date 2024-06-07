package com.example.mod4.room

import androidx.lifecycle.LiveData
import com.example.mod4.utils.AppExecutors

class PostRepository private constructor(private val appDao: PostDao, private val appExecutors: AppExecutors) {

    // Mendapatkan semua data pemain dari database
    fun getAllPost(): LiveData<List<PostEntity>> = appDao.getAllPost()

    // Memasukkan data pemain ke dalam database
    fun insertPost(post: PostEntity) {
        // Menjalankan operasi insert di thread yang berbeda
        appExecutors.diskIO().execute { appDao.insertPost(post) }
    }

    fun updatePost(post: PostEntity){
        appExecutors.diskIO().execute{appDao.updatePost(post) }
    }

    
    fun deletePost(post: PostEntity){
        appExecutors.diskIO().execute{appDao.deletePost(post) }
    }
    companion object {
        // Variabel untuk menyimpan instance dari AppRepository
        @Volatile
        private var instance: PostRepository? = null

        // Mendapatkan instance dari AppRepository
        fun getInstance(
            appDao: PostDao,
            appExecutors: AppExecutors
        ): PostRepository =
            // Jika instance null, maka akan dibuat instance baru
            instance ?: synchronized(this) {
                instance ?: PostRepository(appDao, appExecutors)
            }.also { instance=it}
    }
}
