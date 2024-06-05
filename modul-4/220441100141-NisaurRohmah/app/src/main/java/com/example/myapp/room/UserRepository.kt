package com.example.myapp.room

import androidx.lifecycle.LiveData
import com.example.myapp.utils.AppExecutors


// class utk mengakses DAO yang sudah kita buat
class UserRepository private constructor(private val userDao: UserDao, private val appExecutors: AppExecutors) {

    fun getAllPost(): LiveData<List<UserEntity>> = userDao.getAllPost()

    fun insertPost(post: UserEntity) {
        appExecutors.diskIO().execute { userDao.insertPost(post) }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            userDao: UserDao,
            appExecutors: AppExecutors
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(userDao, appExecutors)
        }.also { instance = it }
    }
}