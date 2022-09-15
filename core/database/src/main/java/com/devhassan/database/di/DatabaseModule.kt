package com.devhassan.database.di

import android.content.Context
import com.devhassan.database.AppRoomDatabase
import com.devhassan.database.dao.DetailsEntityDao
import com.devhassan.database.dao.MovieEntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext appContext: Context
    ): AppRoomDatabase {
        return AppRoomDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun providesMovieEntityDao(
        database: AppRoomDatabase
    ): MovieEntityDao {
        return database.moviesEntityDao()
    }

    @Provides
    @Singleton
    fun providesDetailsEntityDao(
        database: AppRoomDatabase
    ): DetailsEntityDao {
        return database.detailsEntityDao()
    }
}
