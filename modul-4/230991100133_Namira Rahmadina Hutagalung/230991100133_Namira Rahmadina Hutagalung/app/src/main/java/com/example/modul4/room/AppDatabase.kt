package com.example.modul4.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PostDatabase::class], version = 1)

@TypeConverters(AppConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "app_database"
                    )
                        .build()
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}