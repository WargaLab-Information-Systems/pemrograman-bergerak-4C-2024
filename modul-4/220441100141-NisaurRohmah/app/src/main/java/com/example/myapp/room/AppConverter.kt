package com.example.myapp.room

import androidx.room.TypeConverter
import java.io.File

class AppConverter {

    //convert tipe data file ke string
    @TypeConverter
    fun fromFile(file: File?): String? {
        return file?.path
    }

    //convert tipe data string ke file
    @TypeConverter
    fun toFile(path: String?): File? {
        return if (path != null) File(path) else null
    }
}