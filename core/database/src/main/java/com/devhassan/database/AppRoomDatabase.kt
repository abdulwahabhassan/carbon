package com.devhassan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devhassan.database.converter.Converter
import com.devhassan.database.dao.DetailsEntityDao
import com.devhassan.database.dao.MovieEntityDao
import com.devhassan.database.entity.LocalDetailsEntity
import com.devhassan.database.entity.LocalMovieEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Database(
    entities = [LocalMovieEntity::class, LocalDetailsEntity::class],
    version = 1,
    exportSchema = false
)
public abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun moviesEntityDao(): MovieEntityDao

    abstract fun detailsEntityDao(): DetailsEntityDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppRoomDatabase::class.java, "app_database"
                ).addTypeConverter(
                    Converter(
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    )
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}