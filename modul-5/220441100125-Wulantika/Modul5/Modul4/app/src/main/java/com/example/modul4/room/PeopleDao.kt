package com.example.modul4.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PeopleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPeople(peopleEntity: PeopleEntity)

    @Query("SELECT * FROM peopleentity ORDER BY name ASC")
    fun getAllPeople() : LiveData<List<PeopleEntity>>

    @Update
    fun updatePeople(peopleEntity: PeopleEntity)

    @Delete
    fun deletePeople(peopleEntity: PeopleEntity)
}