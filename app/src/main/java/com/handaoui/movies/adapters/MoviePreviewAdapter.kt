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
import com.handaoui.movies.data.Movie
import com.handaoui.movies.R
import com.handaoui.movies.activities.MovieDetailsActivity
import java.util.ArrayList


class MoviesPreviewAdapter(var context: Context,
                           private var moviesList: ArrayList<Movie>) :
        RecyclerView.Adapter<MoviesPreviewAdapter.CustomViewHolder>() {

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val movie: Movie = moviesList[position]
        holder?.imageView?.setImageResource(movie.cover)
        holder?.projectionRoomView?.text = movie.projectRoom?.name
        holder?.titleView?.text = movie.title
        holder?.id = movie.id
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
                val movieDetailsIntent = Intent(context, MovieDetailsActivity::class.java).apply {
                    putExtra("id", id)
                }
                context.startActivity(movieDetailsIntent)
            }
        }
    }
}