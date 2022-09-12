package com.devhassan.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devhassan.database.entity.LocalDetailsEntity

@Dao
interface DetailsEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(localDetailsEntity: LocalDetailsEntity)

    @Query("SELECT * FROM details WHERE id = :id")
    suspend fun getMovieDetailsById(id: Long): LocalDetailsEntity?
}
