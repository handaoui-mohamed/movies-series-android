package com.handaoui.movies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

/**
 * Created by User on 28/03/2018.
 */


class MoviesPreviewAdapter(context:Context, moviesList: ArrayList<MoviePreview>): RecyclerView.Adapter<MoviesPreviewAdapter.CustomViewHolder>(){
    var context = context
    var moviesList = moviesList

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        var moviePreview:MoviePreview = moviesList[position]
        holder?.imageView?.setImageResource(moviePreview.cover)
        holder?.titleView?.text = moviePreview.title
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view:View = LayoutInflater.from(parent?.context).inflate(R.layout.preview, parent, false)
        return CustomViewHolder(view)
    }

    class CustomViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var titleView:TextView = view.findViewById(R.id.titleTxt)
        var imageView:ImageView = view.findViewById(R.id.coverImg)
    }
}