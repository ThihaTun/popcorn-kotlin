package com.example.thiha.popcorn_kotlin.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "bookmark")
data class  Bookmark(
        @PrimaryKey
        @ColumnInfo(name = "imdb_id")
        val imdb: String
)