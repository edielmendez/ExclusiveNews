package com.mx.ediel.exclusivenews.data.remote.news.respose

import com.google.gson.annotations.SerializedName

data class EverythingNewsResponse(
    @SerializedName("status"       ) var status       : String?             = null,
    @SerializedName("totalResults" ) var totalResults : Int?                = null,
    @SerializedName("articles"     ) var articles     : List<NewsDto> = emptyList()
)
