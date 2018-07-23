package com.example.thiha.popcorn_kotlin.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.thiha.popcorn_kotlin.R
import com.example.thiha.popcorn_kotlin.database.UserMovie
import com.example.thiha.popcorn_kotlin.main.mvp.MainPresenter
import com.example.thiha.popcorn_kotlin.main.mvp.view.MainView
import com.example.thiha.popcorn_kotlin.main.mvp.view.MovieAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MovieAdapter.onMovieClicked, MainView {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var emptyTextView: TextView
    private lateinit var errorTextView: TextView
    private lateinit var indeterminateBar: ProgressBar

    private lateinit var mMoviesAdapter: MovieAdapter

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recycler_view)
        emptyTextView = findViewById(R.id.empty)
        errorTextView = findViewById(R.id.error)
        indeterminateBar = findViewById(R.id.indeterminateBar)


        mMoviesAdapter = MovieAdapter(this, this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mMoviesAdapter

        presenter.onCreate()
    }

    override fun showMovies(userMovies: List<UserMovie>) {
        mMoviesAdapter.setData(userMovies)
        emptyTextView.visibility = View.INVISIBLE
        errorTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.INVISIBLE
        mRecyclerView.visibility = View.VISIBLE
    }

    override fun showNoMovies() {
        mRecyclerView.visibility = View.INVISIBLE
        errorTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.INVISIBLE
        emptyTextView.visibility = View.VISIBLE
    }

    override fun showError() {
        mRecyclerView.visibility = View.INVISIBLE
        emptyTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.INVISIBLE
        errorTextView.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mRecyclerView.visibility = View.INVISIBLE
        emptyTextView.visibility = View.INVISIBLE
        errorTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.VISIBLE
    }

    override fun onClick(userMovie: UserMovie) {
        Log.d("Detail", "u clicked")
    }
}
