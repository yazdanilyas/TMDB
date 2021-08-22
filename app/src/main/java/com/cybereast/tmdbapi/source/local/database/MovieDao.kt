package com.cybereast.tmdbapi.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cybereast.tmdbapi.models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(product: ArrayList<Movie>)

    @Query("Select * from Movie")
    fun getAllData(): LiveData<List<Movie>>
}