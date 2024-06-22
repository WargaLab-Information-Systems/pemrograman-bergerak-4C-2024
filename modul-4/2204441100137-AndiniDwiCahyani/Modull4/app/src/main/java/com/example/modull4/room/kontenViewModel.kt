package com.example.modull4.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class kontenViewModel(private val repoSitory: repoSitory) : ViewModel() {
    fun insertKonten(konten: kontenDatabase){
        repoSitory.insertKonten(konten)
    }

    fun getAllKonten(): LiveData<List<kontenDatabase>>{
        return repoSitory.getAllKonten()
    }
}