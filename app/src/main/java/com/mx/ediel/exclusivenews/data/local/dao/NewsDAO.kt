package com.mx.ediel.exclusivenews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mx.ediel.exclusivenews.data.local.entities.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDAO {
    @Query("SELECT * FROM news")
    fun getNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNews(id: Int): NewsEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNews(movie: NewsEntity)

    @Query("DELETE FROM news WHERE id = :id")
    suspend fun deleteNews(id: Int)
}