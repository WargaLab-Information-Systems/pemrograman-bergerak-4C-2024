package com.example.modul4.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PeopleViewModel(private val peopleRepository: PeopleRepository) : ViewModel() {

    // Memasukkan data pemain ke dalam database
    fun insertPlayer(player: PeopleEntity) {
        peopleRepository.insertPlayer(player)
    }

    // Mendapatkan semua data pemain dari database
    fun getAllPlayer(): LiveData<List<PeopleEntity>> {
        return peopleRepository.getAllPlayer()
    }

    fun updatePeople(people: PeopleEntity){
        peopleRepository.updatePeople(people)
    }

    fun deletePeople(people: PeopleEntity){
        peopleRepository.deletePeople(people)
        }
    }