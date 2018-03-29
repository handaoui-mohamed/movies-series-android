package com.handaoui.movies.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.handaoui.movies.Data.Movie
import com.handaoui.movies.R
import java.util.ArrayList


class MoviesPreviewAdapter(var context: Context, private var moviesList: ArrayList<Movie>) : RecyclerView.Adapter<MoviesPreviewAdapter.CustomViewHolder>() {

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        var movie: Movie = moviesList[position]
        holder?.imageView?.setImageResource(movie.cover)
        holder?.titleView?.text = movie.title
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.preview_card, parent, false)
        return CustomViewHolder(view)
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleView: TextView = view.findViewById(R.id.titleTxt)
        var imageView: ImageView = view.findViewById(R.id.coverImg)

        init {
            view.setOnClickListener { Toast.makeText(view.context, "Hello", Toast.LENGTH_LONG).show() }
        }
    }
}