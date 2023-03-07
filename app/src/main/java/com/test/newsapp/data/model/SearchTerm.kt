package com.test.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchterm")
data class SearchTerm(
    @PrimaryKey(autoGenerate = true)
    var queryId: Int = 0,

    @ColumnInfo(name = "query")
    val queryName: String
)
