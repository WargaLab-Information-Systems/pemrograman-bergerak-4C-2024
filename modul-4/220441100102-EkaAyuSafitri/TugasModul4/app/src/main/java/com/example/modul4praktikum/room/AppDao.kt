package com.example.modul4praktikum.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(post:PostDatabase)
    @Query("SELECT * from postdatabase  ORDER BY id DESC")
    fun getAllPost(): LiveData<List<PostDatabase>>
}