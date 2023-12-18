package com.mx.ediel.exclusivenews.ui.model

import android.os.Parcelable
import java.io.Serializable

data class News(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val createdAt: String,
    val image: String
): Serializable
