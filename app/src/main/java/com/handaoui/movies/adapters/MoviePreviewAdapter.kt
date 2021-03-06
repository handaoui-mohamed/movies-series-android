package com.handaoui.movies.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.handaoui.movies.Config
import com.handaoui.movies.data.Movie
import com.handaoui.movies.R
import com.handaoui.movies.activities.MovieDetailsActivity
import com.squareup.picasso.Picasso
import java.util.ArrayList


class MoviesPreviewAdapter(var context: Context,
                           private var moviesList: ArrayList<Movie>) :
        RecyclerView.Adapter<MoviesPreviewAdapter.CustomViewHolder>() {

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val movie: Movie = moviesList[position]
        Picasso.with(context)
                .load(Config.imagePreviewUrl + movie.poster_path)
                .into(holder?.imageView)
        holder?.projectionRoomView?.text = movie.release_date
        holder?.titleView?.text = movie.title
        holder?.id = movie.id
    }

    fun updateList(movies: ArrayList<Movie>) {
        moviesList = movies
        notifyDataSetChanged()
    }

    fun addToList(movies: ArrayList<Movie>) {
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.movie_preview_card, parent, false)
        return CustomViewHolder(view)
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        var titleView: TextView = view.findViewById(R.id.movieTitleTxt)
        var projectionRoomView: TextView = view.findViewById(R.id.projectionRoomTxt)
        var imageView: ImageView = view.findViewById(R.id.coverImg)
        private var cardView: CardView = view.findViewById(R.id.card_view)

        init {
            cardView.setOnClickListener {
                val selectedMovie = moviesList.find { movie -> movie.id == id }
                val movieDetailsIntent = Intent(context, MovieDetailsActivity::class.java).apply {
                    if(selectedMovie != null){
                        putExtra("id", id)
                        putExtra("title", selectedMovie.title)
                        putExtra("overview", selectedMovie.overview)
                        putExtra("release_date", selectedMovie.release_date)
                        putExtra("vote_average", selectedMovie.vote_average)
                        putExtra("poster_path", selectedMovie.poster_path)
                    }
                }
                context.startActivity(movieDetailsIntent)
            }
        }
    }
}