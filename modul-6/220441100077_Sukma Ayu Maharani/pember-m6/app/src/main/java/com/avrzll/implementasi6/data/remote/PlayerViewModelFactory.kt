package com.avrzll.implementasi6.data.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    // Fungsi ini digunakan untuk membuat instance dari ViewModel
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Jika modelClass adalah atau merupakan subclass dari ExampleViewModel
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            // Membuat dan mengembalikan instance dari ExampleViewModel
            return PlayerViewModel(repository) as T
        }
        // Jika modelClass bukan ExampleViewModel atau subclass dari ExampleViewModel, lemparkan pengecualian
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    // Objek companion yang digunakan untuk membuat instance dari ExampleViewModelFactory
    companion object {
        // Variabel instance digunakan untuk menyimpan instance dari ExampleViewModelFactory
        @Volatile
        private var instance: PlayerViewModelFactory? = null

        // Fungsi ini digunakan untuk mendapatkan instance dari ExampleViewModelFactory
        fun getInstance(): PlayerViewModelFactory =
        // Jika instance tidak null, kembalikan instance
            // Jika instance null, buat instance baru
            instance ?: synchronized(this) {
                instance
                    ?: PlayerViewModelFactory(Repository())
            }.also { instance = it }
    }
}
