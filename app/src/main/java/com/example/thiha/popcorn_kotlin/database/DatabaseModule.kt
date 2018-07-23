package com.example.thiha.popcorn_kotlin.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.jvm.java

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): MovieDatabase =
            Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db")
                    .build()

}