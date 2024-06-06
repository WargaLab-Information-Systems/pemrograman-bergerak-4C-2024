package com.example.modul5.room

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
    fun insertPeople(people:PeopleEntity)

    @Query("SELECT * FROM peopleentity ORDER BY description ASC")
    fun getAllPeople() : LiveData<List<PeopleEntity>>

    @Update
    fun updatePeople(people: PeopleEntity)

    @Delete
    fun deletePeople(people: PeopleEntity)

}