package com.example.myapp.utils

import android.content.Context
import com.example.myapp.room.UserDatabase
import com.example.myapp.room.UserRepository


//object untuk menyediakan dependensi ke dalam kelas-kelas yang membutuhkannya
object DependencyInjection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val appExecutors = AppExecutors()
        val dao = database.userDao()
        return UserRepository.getInstance(dao, appExecutors)
    }
}