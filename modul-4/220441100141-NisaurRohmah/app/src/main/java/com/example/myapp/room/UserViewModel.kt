package com.example.myapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

// class utk pemisahan antara data dan UI
class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    fun insertPost(userEntity: UserEntity) {
        userRepository.insertPost(userEntity)
    }

    fun getAllPost(): LiveData<List<UserEntity>> {
        return userRepository.getAllPost()
    }
}