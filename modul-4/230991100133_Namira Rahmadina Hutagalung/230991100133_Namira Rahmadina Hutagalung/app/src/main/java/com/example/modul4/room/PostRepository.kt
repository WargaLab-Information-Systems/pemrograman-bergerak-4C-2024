package com.example.modul4.room

import androidx.lifecycle.LiveData
import com.example.modul4.utils.AppExecutors

class PostRepository private constructor(private val postDao: PostDao, private val appExecutors: AppExecutors) {

    fun getAllPost(): LiveData<List<PostDatabase>> = postDao.getAllPost()

    fun insertPost(post: PostDatabase) {
        appExecutors.diskIO().execute { postDao.insertPost(post) }
    }

    fun deletePost(post: PostDatabase) {
        appExecutors.diskIO().execute { postDao.deletePost(post) }
    }

    companion object {
        @Volatile
        private var instance: PostRepository? = null

        fun getInstance(
            postDao: PostDao,
            appExecutors: AppExecutors
        ): PostRepository = instance ?: synchronized(this) {
                instance ?: PostRepository(postDao, appExecutors)
            }.also { instance = it }
    }
}