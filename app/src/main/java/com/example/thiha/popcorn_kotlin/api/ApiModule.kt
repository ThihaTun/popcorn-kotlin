package com.example.thiha.popcorn_kotlin.api

import com.example.thiha.popcorn_kotlin.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideMovieClient(): MovieClient = MovieClient(BuildConfig.BASE_URL, BuildConfig.API_KEY)




}