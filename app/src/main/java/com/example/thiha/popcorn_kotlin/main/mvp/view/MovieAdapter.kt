package com.example.thiha.popcorn_kotlin.main.mvp.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.thiha.popcorn_kotlin.R
import com.example.thiha.popcorn_kotlin.database.UserMovie
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context, private val listener: onMovieClicked)
    : RecyclerView.Adapter<MovieAdapter.Companion.MovieViewHolder>() {

    companion object {
        class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val mTitle: TextView = view.findViewById(R.id.title)
            val plot: TextView = view.findViewById(R.id.plot)
            val image: ImageView = view.findViewById(R.id.image)
            val bookmarked: ImageView = view.findViewById(R.id.bookmarked)
        }
    }

    private var userMovies: List<UserMovie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = userMovies[position]

        holder.mTitle.text = item.title
        holder.plot.text = item.plot
        Picasso.with(context).load(item.poster).fit().centerInside().into(holder.image)
        holder.itemView.setOnClickListener { _ -> listener.onClick(item) }
        holder.bookmarked.visibility = if (item.bookmarked) VISIBLE else GONE
        holder.bookmarked.setColorFilter(R.color.colorBookmarked)
    }

    override fun getItemCount(): Int {
        return userMovies.count()
    }

    fun setData(userMovies: List<UserMovie>) {
        this.userMovies = userMovies
        notifyDataSetChanged()
    }

    interface onMovieClicked {
        fun onClick(userMovie: UserMovie)
    }
}