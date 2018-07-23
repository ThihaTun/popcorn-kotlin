package com.example.thiha.popcorn_kotlin.main

import com.example.thiha.popcorn_kotlin.api.MovieClient
import com.example.thiha.popcorn_kotlin.database.MovieDatabase
import com.example.thiha.popcorn_kotlin.main.mvp.MainModel
import com.example.thiha.popcorn_kotlin.main.mvp.MainPresenter
import com.example.thiha.popcorn_kotlin.main.mvp.view.MainView
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideModel(database: MovieDatabase, client: MovieClient) = MainModel(database, client)

    @MainScope
    @Provides
    fun provideView(activity: MainActivity) = activity as MainView

    @MainScope
    @Provides
    fun providePresenter(model: MainModel, view: MainView) = MainPresenter(model, view)
}