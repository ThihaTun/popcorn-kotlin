package com.example.thiha.popcorn_kotlin.moviedetail.mvp

import com.example.thiha.popcorn_kotlin.database.Bookmark
import com.example.thiha.popcorn_kotlin.database.MovieDatabase
import com.example.thiha.popcorn_kotlin.database.UserMovie
import io.reactivex.Observable

class MovieDetailModel(private val movieDatabase: MovieDatabase, val userMovie: UserMovie) {

    fun bookmarkMovie(): Observable<Any> =
            Observable.create<Any> {
                movieDatabase.bookmarkDao().insert(Bookmark(userMovie.imdbId))
                movieDatabase.userMovieDao().insert(
                        UserMovie(userMovie.imdbId, userMovie.title, userMovie.actors,
                                userMovie.plot, userMovie.imdbRating, userMovie.poster,
                                true))
                if (!it.isDisposed()) {
                    it.onNext(0)
                }
            }

    fun unBookmarkMovie(): Observable<Any> =
            Observable.create<Any> {
                movieDatabase.bookmarkDao().insert(Bookmark(userMovie.imdbId))
                movieDatabase.userMovieDao().insert(
                        UserMovie(userMovie.imdbId, userMovie.title, userMovie.actors,
                                userMovie.plot, userMovie.imdbRating, userMovie.poster,
                                false))
                if (!it.isDisposed()) {
                    it.onNext(0)
                }
            }
}