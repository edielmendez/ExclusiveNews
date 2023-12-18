package com.mx.ediel.exclusivenews.data.remote.news.respose

import com.google.gson.annotations.SerializedName
import com.mx.ediel.exclusivenews.ui.model.News

data class NewsDto(
    @SerializedName("source"      ) var source      : Source? = Source(),
    @SerializedName("author"      ) var author      : String? = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("url"         ) var url         : String? = null,
    @SerializedName("urlToImage"  ) var urlToImage  : String? = null,
    @SerializedName("publishedAt" ) var publishedAt : String? = null,
    @SerializedName("content"     ) var content     : String? = null
){
    fun toNews() = News(
        id = 0,
        title = title ?: "",
        author = author ?: "",
        description = description ?: "",
        createdAt = publishedAt ?: "",
        image = urlToImage ?: ""
    )
}

data class Source (

    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("name" ) var name : String? = null

)