package com.example.modul4.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    fun insertPost(post: PostDatabase) {
        postRepository.insertPost(post) //untuk menyisipkan data
    }

    fun getAllPost(): LiveData<List<PostDatabase>> {
        return postRepository.getAllPost() //untuk mendapatkan postingan dari database
    }

    fun deletePost(post: PostDatabase) {
        postRepository.deletePost(post) //untuk menghapus data dari database
    }
}