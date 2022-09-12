package com.devhassan.data.datasource.local

import com.devhassan.data.datasource.MoviesDataSrc
import com.devhassan.database.dao.DetailsEntityDao
import com.devhassan.database.dao.MovieEntityDao
import com.devhassan.database.entity.LocalDetailsEntity
import com.devhassan.database.entity.LocalMovieEntity
import javax.inject.Inject

class LocalDataSrc @Inject constructor(
    private val moviesEntityDao: MovieEntityDao,
    private val detailsEntityDao: DetailsEntityDao
) : MoviesDataSrc<List<LocalMovieEntity>, LocalDetailsEntity> {

    override suspend fun fetchMovies(category: String): List<LocalMovieEntity> {
        return moviesEntityDao.getMoviesByCategory(category)
    }

    override suspend fun fetchMovieDetails(id: Long): LocalDetailsEntity {
        return detailsEntityDao.getMovieDetailsById(id)
    }

    suspend fun insertMovie(localMovieEntity: LocalMovieEntity) {
        moviesEntityDao.insertMovie(localMovieEntity)
    }

    suspend fun insertDetails(localDetailsEntity: LocalDetailsEntity) {
        detailsEntityDao.insertMovieDetails(localDetailsEntity)
    }

}