package com.example.modul4.room

import androidx.room.TypeConverter
import java.io.File

class AppConverter {

    @TypeConverter
    fun fromFile(file: File?): String? {
        return file?.path
    }

    @TypeConverter
    fun toFile(path: String?): File? {
        return if (path != null) File(path) else null
    }
}