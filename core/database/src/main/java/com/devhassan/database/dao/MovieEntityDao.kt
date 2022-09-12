package com.devhassan.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devhassan.database.entity.LocalMovieEntity

@Dao
interface MovieEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(localMovieEntity: LocalMovieEntity)

    @Query("SELECT * FROM movie WHERE category = :category")
    suspend fun getMoviesByCategory(category: String): List<LocalMovieEntity>


}
