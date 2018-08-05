package com.example.thiha.popcorn_kotlin.moviedetail

import com.example.thiha.popcorn_kotlin.database.MovieDatabase
import com.example.thiha.popcorn_kotlin.database.UserMovie
import com.example.thiha.popcorn_kotlin.moviedetail.mvp.MovieDetailModel
import com.example.thiha.popcorn_kotlin.moviedetail.mvp.MovieDetailPresenter
import com.example.thiha.popcorn_kotlin.moviedetail.mvp.MovieDetailView
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule {

    @MovieDetailScope
    @Provides
    fun provideUserMovie(activity: MovieDetailActivity): UserMovie {
        if (!activity.intent.hasExtra(MovieDetailActivity.EXTRA_USER_MOVIE)) {
            throw IllegalArgumentException("Activity is missing ")
        }
        return activity.intent.extras.getParcelable(MovieDetailActivity.EXTRA_USER_MOVIE)
    }

    @MovieDetailScope
    @Provides
    fun provideMovieDetailModel(database: MovieDatabase, userMovie: UserMovie)
            = MovieDetailModel(database, userMovie)

    @MovieDetailScope
    @Provides
    fun provideMovieDetailView(activity: MovieDetailActivity) = MovieDetailView(activity)

    @MovieDetailScope
    @Provides
    fun provideMovieDetailsPresenter(model: MovieDetailModel, view: MovieDetailView)
            = MovieDetailPresenter(model, view)

}