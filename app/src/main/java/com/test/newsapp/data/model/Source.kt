package com.test.newsapp.data.model

import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo(name = "name")
    val name: String
)
