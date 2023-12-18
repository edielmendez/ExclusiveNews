package com.mx.ediel.exclusivenews.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mx.ediel.exclusivenews.ui.model.News

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "created_at") val created_at: String?,
    @ColumnInfo(name = "image") val image: String?,
){
    fun toNewsModel() = News(
        id = id ?: 0,
        title = title ?: "",
        author = author ?: "",
        description = description ?: "",
        createdAt = created_at ?: "",
        image = image ?: ""
    )
}
