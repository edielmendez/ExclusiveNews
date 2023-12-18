package com.mx.ediel.exclusivenews.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mx.ediel.exclusivenews.data.local.dao.NewsDAO
import com.mx.ediel.exclusivenews.data.local.entities.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class ExclusiveNewsDatabase: RoomDatabase(){
    abstract fun newsDAO(): NewsDAO

    companion object {
        private const val DB_NAME = "CCC_APP_DB_ROOM.db"

        // For Singleton instantiation
        @Volatile private var instance: ExclusiveNewsDatabase? = null

        fun getInstance(context: Context): ExclusiveNewsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ExclusiveNewsDatabase {
            return Room.databaseBuilder(context, ExclusiveNewsDatabase::class.java, DB_NAME)
                .build()
        }
    }
}