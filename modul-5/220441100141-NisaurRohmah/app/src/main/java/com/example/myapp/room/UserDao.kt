package com.example.myapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

// Fungsi ini digunakan untuk memasukkan data orang ke dalam database.
// Anotasi @Insert memberi tahu Room bahwa fungsi ini digunakan untuk memasukkan data.
// Parameter onConflict digunakan untuk menentukan apa yang harus dilakukan Room jika data yang dimasukkan memiliki konflik dengan data yang sudah ada di database.
// OnConflictStrategy.IGNORE berarti jika ada konflik, Room akan mengabaikan operasi insert.
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity ORDER BY id DESC")
    fun getAllPost() : LiveData<List<UserEntity>>

    @Update
    fun updatePost(userEntity: UserEntity)

    @Delete
    fun deletePost(userEntity: UserEntity)

}