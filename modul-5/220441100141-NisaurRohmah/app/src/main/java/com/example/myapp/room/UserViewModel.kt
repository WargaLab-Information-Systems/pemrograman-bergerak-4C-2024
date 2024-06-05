package com.example.myapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

// Kelas PeopleViewModel adalah kelas yang bertugas untuk menghubungkan Repository dan UI.
// Kelas ini mewarisi ViewModel, yang merupakan kelas dari Android Architecture Components yang digunakan untuk menyimpan dan mengelola data yang terkait dengan UI.
class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    // Fungsi insertPeople digunakan untuk memasukkan data orang ke dalam database.
    // Fungsi ini menerima parameter berupa objek PeopleEntity dan memanggil fungsi insertPlayer dari PeopleRepository.
    fun insertPost(userEntity: UserEntity) {
        userRepository.insertPost(userEntity)
    }

    // Fungsi getAllPeople digunakan untuk mendapatkan semua data orang dari database.
    // Fungsi ini mengembalikan LiveData yang berisi daftar semua orang.
    // LiveData adalah kelas dari Android Architecture Components yang memungkinkan kita untuk mengamati perubahan data dalam database dan secara otomatis memperbarui UI jika ada perubahan.
    fun getAllPost(): LiveData<List<UserEntity>> {
        return userRepository.getAllPost()
    }

    fun updatePost(userEntity: UserEntity) {
        userRepository.updatePost(userEntity)
    }

    fun deletePost(userEntity: UserEntity) {
        userRepository.deletePost(userEntity)
    }

}