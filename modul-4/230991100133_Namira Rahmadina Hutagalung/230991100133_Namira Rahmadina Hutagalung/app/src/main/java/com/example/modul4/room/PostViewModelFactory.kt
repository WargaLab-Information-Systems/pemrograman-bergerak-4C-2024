package com.example.modul4.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul4.utils.DependencyInjection


class PostViewModelFactory private constructor(private val postRepository: PostRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(postRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: PostViewModelFactory? = null
        fun getInstance(context: Context): PostViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: PostViewModelFactory(DependencyInjection.provideRepository(context))
            }.also { instance = it }
    }
}