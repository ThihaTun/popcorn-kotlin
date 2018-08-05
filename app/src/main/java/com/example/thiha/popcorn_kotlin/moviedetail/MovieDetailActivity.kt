package com.example.thiha.popcorn_kotlin.moviedetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.thiha.popcorn_kotlin.moviedetail.mvp.MovieDetailPresenter
import com.example.thiha.popcorn_kotlin.moviedetail.mvp.MovieDetailView
import dagger.android.AndroidInjection
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER_MOVIE = "user_movie"
    }

    @Inject
    lateinit var view: MovieDetailView

    @Inject
    lateinit var presenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(view)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
