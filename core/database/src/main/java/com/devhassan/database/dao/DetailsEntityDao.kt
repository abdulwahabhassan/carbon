package com.devhassan.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devhassan.database.entity.DetailsEntity
import com.devhassan.database.entity.MovieEntity

@Dao
interface DetailsEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(detailsEntity: DetailsEntity)

    @Query("SELECT * FROM details WHERE id = :id")
    suspend fun getMovieDetailsById(id: Long): DetailsEntity
}
