package com.example.thiha.popcorn_kotlin.api

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class MovieClient(baseUrl: String, apiKey: String) {

    private val service: MovieService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder()
                        .addInterceptor(ApiKeyInterceptor(apiKey))
                        .build())
                .build()

        service = retrofit.create(MovieService::class.java)
    }

    fun searchMovie(searchKey: String, year: String, type: String): Observable<SearchResult> = service.searchMovies(searchKey, year, type)
    fun getMovie(imdbId: String) : Observable<Movie> = service.getMovie(imdbId)
}