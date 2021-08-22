package com.cybereast.tmdbapi.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cybereast.tmdbapi.constant.Constants.APP_DATABASE_NAME
import com.cybereast.tmdbapi.models.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao


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