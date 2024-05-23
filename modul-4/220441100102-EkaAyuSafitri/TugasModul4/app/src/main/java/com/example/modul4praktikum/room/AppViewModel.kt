package com.example.modul4praktikum.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class AppViewModel(private val appRepository: AppRepository) : ViewModel() {
    fun insertPost(post: PostDatabase) {
        appRepository.insertPost(post)
    }

    fun getAllPost(): LiveData<List<PostDatabase>> {
        return appRepository.getAllPost()
    }
}
