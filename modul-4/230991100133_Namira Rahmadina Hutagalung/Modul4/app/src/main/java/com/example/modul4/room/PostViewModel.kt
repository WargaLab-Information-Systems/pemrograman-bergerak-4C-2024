package com.example.modul4.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    fun insertPost(post: PostDatabase) {
        postRepository.insertPost(post)
    }

    fun getAllPost(): LiveData<List<PostDatabase>> {
        return postRepository.getAllPost()
    }

    fun deletePost(post: PostDatabase) {
        postRepository.deletePost(post)
    }
}