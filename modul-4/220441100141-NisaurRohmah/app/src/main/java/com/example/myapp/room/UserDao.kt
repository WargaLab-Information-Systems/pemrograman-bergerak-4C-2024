package com.example.myapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//untuk mendefinisikan operasi database, seperti insert, update, delete, dan query.
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(userEntity: UserEntity)

    @Query("SELECT * FROM userentity ORDER BY id DESC")
    fun getAllPost() : LiveData<List<UserEntity>>
}