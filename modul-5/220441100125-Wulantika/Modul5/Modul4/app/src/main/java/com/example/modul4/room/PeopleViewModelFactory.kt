package com.example.modul4.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul4.utils.DependencyInjection

class PeopleViewModelFactory private constructor(private val appRepository: PeopleRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleViewModel::class.java)) {
            return PeopleViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: PeopleViewModelFactory? = null
        fun getInstance(context: Context): PeopleViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: PeopleViewModelFactory(DependencyInjection.provideRepository(context))
            }.also { instance=it}
        }
}