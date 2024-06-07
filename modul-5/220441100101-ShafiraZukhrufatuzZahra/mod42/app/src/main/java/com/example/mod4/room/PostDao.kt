package com.example.mod4.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PostDao {
    //untuk memasukan data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(postEntity: PostEntity)
    //untuk menampilkan dan mendapatkan data
    @Query("SELECT * FROM postentity ORDER BY name ASC")
    fun getAllPost() : LiveData<List<PostEntity>>
    //untuk menghapus data
    @Delete
    fun deletePost(postEntity: PostEntity)
   // untuk mengupdate data
    @Update
    fun updatePost(postEntity: PostEntity)
}