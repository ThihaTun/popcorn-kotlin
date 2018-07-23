package com.example.thiha.popcorn_kotlin.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    fun searchMovies(
            @Query("s") searchKey: String,
            @Query("y") year: String,
            @Query("type") type: String
    ) : Observable<SearchResult>

    @GET("/")
    fun getMovie (
            @Query("i") id: String
    ) : Observable<Movie>
}