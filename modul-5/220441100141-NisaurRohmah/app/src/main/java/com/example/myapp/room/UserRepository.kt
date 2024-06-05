package com.example.myapp.room

import androidx.lifecycle.LiveData
import com.example.myapp.utils.AppExecutors

// Kelas PeopleRepository adalah kelas yang bertugas untuk menghubungkan Dao dan ViewModel.
// Kelas ini menggunakan pola desain Repository, yang merupakan pola desain yang disarankan oleh Google untuk memastikan bahwa aplikasi Anda dapat bekerja dengan berbagai sumber data.
class UserRepository private constructor(
    // Variabel appDao adalah instance dari PeopleDao yang akan digunakan untuk mengakses database.
    private val userDao: UserDao,
    // Variabel appExecutors adalah instance dari AppExecutors yang akan digunakan untuk menjalankan operasi database di thread yang berbeda dari thread UI.
    private val appExecutors: AppExecutors) {

    // Fungsi getAllPeople digunakan untuk mendapatkan semua data orang dari database.
    // Fungsi ini mengembalikan LiveData yang berisi daftar semua orang.
    fun getAllPost(): LiveData<List<UserEntity>> = userDao.getAllPost()

    // Fungsi insertPlayer digunakan untuk memasukkan data orang ke dalam database.
    // Fungsi ini menjalankan operasi insert di thread yang berbeda dari thread UI menggunakan AppExecutors.
    fun insertPost(userEntity: UserEntity) {
        appExecutors.diskIO().execute { userDao.insertPost(userEntity) }
    }

    fun updatePost(userEntity: UserEntity) {
        appExecutors.diskIO().execute { userDao.updatePost(userEntity) }
    }

    fun deletePost(userEntity: UserEntity) {
        appExecutors.diskIO().execute { userDao.deletePost(userEntity) }
    }

    // Ini adalah objek companion yang berisi fungsi getInstance.
    // Fungsi getInstance digunakan untuk mendapatkan instance dari PeopleRepository.
    // Jika instance sudah ada, fungsi ini akan mengembalikan instance tersebut.
    // Jika instance belum ada, fungsi ini akan membuat instance baru.
    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            userDao: UserDao,
            appExecutors: AppExecutors
        ): UserRepository =
        // Jika instance sudah ada, kembalikan instance tersebut.
            // Jika instance belum ada, buat instance baru.
            instance ?: synchronized(this) {
            instance ?: UserRepository(userDao, appExecutors)
        }.also { instance = it }

    }
}