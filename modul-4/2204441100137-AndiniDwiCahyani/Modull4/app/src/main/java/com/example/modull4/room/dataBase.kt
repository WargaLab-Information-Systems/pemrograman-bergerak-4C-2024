package com.example.modull4.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [kontenDatabase::class], version = 1)

@TypeConverters(konverter::class)

abstract class dataBase : RoomDatabase() {
    abstract fun kontenDao() : kontenDao

    companion object{
        @Volatile
        private var INSTANCE: dataBase? = null

        @JvmStatic
        fun getDatabase(context: Context): dataBase{
            if(INSTANCE == null){
                synchronized(dataBase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        dataBase::class.java, "app_database"
                    )
                        .build()
                }
            }
            return INSTANCE as dataBase
        }
    }
}