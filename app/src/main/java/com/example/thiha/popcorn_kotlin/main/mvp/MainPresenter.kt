package com.example.thiha.popcorn_kotlin.main.mvp

import android.util.Log
import com.example.thiha.popcorn_kotlin.main.mvp.view.MainView
import com.jorpaspr.movies.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val movieModel: MainModel, private val view: MainView): BasePresenter() {

    companion object {
        private val LOG_TAG = MainPresenter::class.java.simpleName
    }

    override fun onCreate() {
        view.showLoading()
        loadMovie()
        updateMovies()
    }

    private fun loadMovie() {
        val disposable = movieModel.loadUserMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            userMovies ->
                            if (userMovies.isEmpty()) {
                                view.showNoMovies()
                            } else {
                                view.showMovies(userMovies)
                            }
                        }
                )
        addDisposable(disposable)
    }

    private fun updateMovies() {
        val disposable = movieModel.searchMovies()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { movie ->
                            Log.d(LOG_TAG, "Movie persisted: ${movie.imdbId}")
                        },
                        { _ ->
                            Log.d(LOG_TAG, "Failed retrieving movie from network and persisting it")
                        },
                        {
                            Log.d(LOG_TAG, "On complete")
                        })
        addDisposable(disposable)
    }
}