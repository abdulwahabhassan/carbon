package com.devhassan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devhassan.database.dao.DetailsEntityDao
import com.devhassan.database.dao.MovieEntityDao
import com.devhassan.database.entity.DetailsEntity
import com.devhassan.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, DetailsEntity::class],
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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}