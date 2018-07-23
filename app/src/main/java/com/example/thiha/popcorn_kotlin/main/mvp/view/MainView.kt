package com.example.thiha.popcorn_kotlin.main.mvp.view

import com.example.thiha.popcorn_kotlin.database.UserMovie

interface MainView {

    fun showMovies(userMovies: List<UserMovie>)
    fun showNoMovies()
    fun showError()
    fun showLoading()
}