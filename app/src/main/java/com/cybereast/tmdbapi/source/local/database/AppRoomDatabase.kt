package com.cybereast.tmdbapi.source.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cybereast.tmdbapi.constant.Constants.APP_DATABASE_NAME

//Uncomment this code when need to integrate local db

/*@Database(
    entities = [],
        exportSchema = false,
        version = 1
)*/
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun baseDao(): BaseDao


    companion object {
        private lateinit var INSTANCE: AppRoomDatabase
        fun getDatabaseInstance(context: Context): AppRoomDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    APP_DATABASE_NAME
                )
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            // INSTANCE = null handle later
        }

        private const val TAG = "AppRoomDatabase"
    }

}