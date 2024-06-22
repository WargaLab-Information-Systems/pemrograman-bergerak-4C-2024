package com.example.modull4.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modull4.utils.DepedencyInjection

class kontenViewModelFactory private constructor(private val repoSitory: repoSitory) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(kontenViewModel::class.java)){
            return kontenViewModel(repoSitory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: kontenViewModelFactory? = null
        fun getInstance(context: Context): kontenViewModelFactory =
            instance ?: synchronized(this){
                instance ?: kontenViewModelFactory(DepedencyInjection.provideRepository(context))
            }.also { instance = it }
    }
}