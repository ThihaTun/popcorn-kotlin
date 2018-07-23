package com.example.thiha.popcorn_kotlin.main.mvp

import com.example.thiha.popcorn_kotlin.api.Movie
import com.example.thiha.popcorn_kotlin.api.MovieClient
import com.example.thiha.popcorn_kotlin.api.MovieService
import com.example.thiha.popcorn_kotlin.api.SearchResult
import com.example.thiha.popcorn_kotlin.database.MovieDatabase
import com.example.thiha.popcorn_kotlin.database.UserMovie
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

open class MainModel(private val movieDatabase: MovieDatabase, private val movieClient: MovieClient) {

    companion object {
        private const val SEARCH_KEY = "star"
        private const val YEAR = "2016"
        private const val TYPE ="movie"
    }

    fun searchMovies(): Observable<Movie> =
            getMovieListBySearch(SEARCH_KEY, YEAR, TYPE)
                    .flatMapIterable { movies -> movies }
                    .flatMap { movie -> getMovie(movie.imdbId) }
                    .flatMap { movie -> persistMovie(movie).toObservable() }

    fun getMovieListBySearch(searchKey: String, year: String, type: String) : Observable<List<Movie>> =
            movieClient.searchMovie(searchKey, year, type)
                    .map { searchResult: SearchResult -> searchResult.search }
                    .map { searches ->
                        val movies = mutableListOf<Movie>()
                        for (search in searches) {
                            movies.add(Movie(search.imdbID, search.title, "", "", "", search.poster))
                        }
                        movies
                    }

    private fun getMovie(imdbId: String) : Observable<Movie> =
        movieClient.getMovie(imdbId)

    private fun persistMovie(movie: Movie): Single<Movie> =
            movieDatabase.bookmarkDao().loadimdbId(movie.imdbId)
                    .isEmpty
                    .map { notBookmarked ->
                        movieDatabase.userMovieDao().insert(UserMovie(
                                movie.imdbId, movie.title, movie.actors, movie.plot, movie.imdbRating,
                                movie.poster, !notBookmarked))
                        movie
                    }

    fun loadUserMovies(): Flowable<List<UserMovie>> =
            movieDatabase.userMovieDao().loadAll()

}