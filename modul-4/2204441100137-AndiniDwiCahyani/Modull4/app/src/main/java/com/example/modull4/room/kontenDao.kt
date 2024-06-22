package com.example.modull4.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface kontenDao {
    /**
     * Fungsi insertKonten digunakan untuk memasukkan data pemain ke dalam database.
     * Anotasi @Insert digunakan untuk memberi tahu Room bahwa fungsi ini digunakan untuk memasukkan data.
     * Parameter onConflict digunakan untuk menentukan apa yang harus dilakukan Room jika data yang dimasukkan memiliki konflik dengan data yang sudah ada di database.
     * OnConflictStrategy.IGNORE berarti jika ada konflik, Room akan mengabaikan operasi insert.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertKonten(konten: kontenDatabase)

    /**
     * Fungsi getAllKonten digunakan untuk mendapatkan semua data pemain dari database.
     * Anotasi @Query digunakan untuk memberi tahu Room bahwa fungsi ini digunakan untuk menjalankan query SQL.
     * Query "SELECT * from playerdatabase ORDER BY player_name ASC" berarti memilih semua data dari tabel playerdatabase dan mengurutkannya berdasarkan player_name dalam urutan ascending.
     * Fungsi ini mengembalikan LiveData yang berisi daftar semua pemain. LiveData adalah kelas dari Android Architecture Components yang memungkinkan kita untuk mengamati perubahan data dalam database dan secara otomatis memperbarui UI jika ada perubahan.
     */
    @Query("SELECT * from kontenDatabase ORDER BY id ASC")
    fun getAllKonten(): LiveData<List<kontenDatabase>>

    @Update
    fun updateKonten(konten: kontenDatabase)
}