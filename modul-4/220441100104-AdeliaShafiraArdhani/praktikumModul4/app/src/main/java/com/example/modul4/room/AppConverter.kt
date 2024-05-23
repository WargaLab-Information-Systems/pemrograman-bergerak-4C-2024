package com.example.modul4.room

import androidx.room.TypeConverter
import java.io.File

class AppConverter {
//untuk mempermudah interaksi dengan database SQLite
    @TypeConverter //mengubah file ke tipe data string
    fun fromFile(file: File?): String? {
        return file?.path
    }

    @TypeConverter //sebaliknya
    fun toFile(path: String?): File? {
        return if (path != null) File(path) else null
    }
}