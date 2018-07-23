package com.example.thiha.popcorn_kotlin.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Bookmark::class, UserMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun userMovieDao(): UserMovieDao
    abstract fun bookmarkDao(): BookmarkDao
}