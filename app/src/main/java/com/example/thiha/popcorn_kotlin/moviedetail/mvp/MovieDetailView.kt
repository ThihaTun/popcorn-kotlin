package com.example.thiha.popcorn_kotlin.moviedetail.mvp

import android.content.Context
import android.graphics.PorterDuff
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.thiha.popcorn_kotlin.R
import com.example.thiha.popcorn_kotlin.database.UserMovie
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_detail.view.*

class MovieDetailView(context: Context?): FrameLayout(context) {

    private val title: TextView
    private val rating: TextView
    private val plot: TextView
    private val actors: TextView
    private val bookmarkButoon: FloatingActionButton
    private val posterImageView: ImageView

    val clickBookmarkButtonObservable: Observable<Any>

    init { private val title: TextView
    private val rating: TextView
    private val plot: TextView
    private val actors: TextView
    private val bookmarkButoon: FloatingActionButton
    private val posterImageView: ImageView

    val clickBookmarkButtonObservable: Observable<Any>

    init {
        inflate(context, R.layout.activity_detail, this)
        title = findViewById(R.id.title)
        plot = findViewById(R.id.plot)
        actors = findViewById(R.id.actors)
        rating = findViewById(R.id.rating)
        bookmarkButoon = findViewById(R.id.bookmark)
        posterImageView = findViewById(R.id.poster)
        clickBookmarkButtonObservable = RxView.clicks(bookmarkButoon)
    }

    fun showUserMovie(userMovie: UserMovie) {
//        (context as Activity).title = movie.title
        title.text = userMovie.title
        plot.text = userMovie.plot
        rating.text = context.getString(R.string.rating, userMovie.imdbRating)
        actors.text = context.getString(R.string.movie_details_actors, userMovie.actors)
        Picasso.with(context).load(userMovie.poster).fit().centerInside().into(poster)
    }

    fun showBookmarked(bookmarked: Boolean) {
        val color: Int =
                if (bookmarked) ContextCompat.getColor(context, R.color.colorBookmarked)
                else ContextCompat.getColor(context, R.color.colorNotBookmarked)

        bookmark.drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }

    fun enableBookmarkButton(enabled: Boolean) {
        bookmarkButoon.isEnabled = enabled
    }
        inflate(context, R.layout.activity_detail, this)
        title = findViewById(R.id.title)
        plot = findViewById(R.id.plot)
        actors = findViewById(R.id.actors)
        rating = findViewById(R.id.rating)
        bookmarkButoon = findViewById(R.id.bookmark)
        posterImageView = findViewById(R.id.poster)
        clickBookmarkButtonObservable = RxView.clicks(bookmarkButoon)
    }

    fun showUserMovie(userMovie: UserMovie) {
//        (context as Activity).title = movie.title
        title.text = userMovie.title
        plot.text = userMovie.plot
        rating.text = context.getString(R.string.rating, userMovie.imdbRating)
        actors.text = context.getString(R.string.movie_details_actors, userMovie.actors)
        Picasso.with(context).load(userMovie.poster).fit().centerInside().into(poster)
    }

    fun showBookmarked(bookmarked: Boolean) {
        val color: Int =
                if (bookmarked) ContextCompat.getColor(context, R.color.colorBookmarked)
                else ContextCompat.getColor(context, R.color.colorNotBookmarked)

        bookmark.drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }

    fun enableBookmarkButton(enabled: Boolean) {
        bookmarkButoon.isEnabled = enabled
    }
}