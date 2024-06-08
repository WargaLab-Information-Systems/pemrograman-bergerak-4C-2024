package com.example.modul4.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PeopleViewModel(private val appRepository: PeopleRepository) : ViewModel() {

    // Memasukkan data pemain ke dalam database
    fun insertPlayer(player: PeopleEntity) {
        appRepository.insertPlayer(player)
    }

    // Mendapatkan semua data pemain dari database
    fun getAllPlayer(): LiveData<List<PeopleEntity>> {
        return appRepository.getAllPlayer()
        }
    }