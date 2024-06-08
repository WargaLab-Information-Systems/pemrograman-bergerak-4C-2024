package com.example.tugasmodul6.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PracticeViewModelFactory private constructor(private val practiceRepository: PracticeRepository) :
    ViewModelProvider.NewInstanceFactory() {

        // Fungsi ini digunakan untuk membuat instance dari ViewModel
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            // Jika modelClass adalah atau merupakan subclass dari ExampleViewModel
            if (modelClass.isAssignableFrom(PracticeViewModel::class.java)) {
                // Membuat dan mengembalikan instance dari ExampleViewModel
                return PracticeViewModel(practiceRepository) as T
            }
            // Jika modelClass bukan ExampleViewModel atau subclass dari ExampleViewModel, lemparkan pengecualian
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

        // Objek companion yang digunakan untuk membuat instance dari PracticeViewModelFactory
        companion object {
            // Variabel instance digunakan untuk menyimpan instance dari PracticeViewModelFactory
            @Volatile
            private var instance: PracticeViewModelFactory? = null

            // Fungsi ini digunakan untuk mendapatkan instance dari PracticeViewModelFactory
            fun getInstance(): PracticeViewModelFactory =
            // Jika instance tidak null, kembalikan instance
                // Jika instance null, buat instance baru
                instance ?: synchronized(this) {
                    instance
                        ?: PracticeViewModelFactory(PracticeRepository())
                }.also { instance = it }
        }
    }