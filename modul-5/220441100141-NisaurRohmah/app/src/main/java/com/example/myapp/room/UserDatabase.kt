package com.example.myapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

    //mendeklarasikan database dengan entitas UserEntity
    @Database(entities = [UserEntity::class], version = 1)

    //konverter utk mengubah tipe data file mjd string atau sebaliknya
    @TypeConverters(AppConverter::class)

    //abstract class userdatabase turunan dari roomdatabase
    abstract class UserDatabase : RoomDatabase() {

        abstract fun userDao(): UserDao

        companion object {
            @Volatile
            private var INSTANCE: UserDatabase? = null

            @JvmStatic
            fun getDatabase(context: Context): UserDatabase {
                if (INSTANCE == null) {
                    synchronized(UserDatabase::class.java) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java, "app_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
                return INSTANCE as UserDatabase
            }
        }
    }