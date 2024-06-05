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
    // Fungsi deletePost digunakan untuk menghapus data post dari database.
    fun deletePost(post: PostDatabase) {
        appRepository.deletePost(post)
    }
    // Fungsi updatePost digunakan untuk memperbarui data post dalam database.
    fun updatePost(post: PostDatabase) {
        appRepository.updatePost(post)
    }
}
