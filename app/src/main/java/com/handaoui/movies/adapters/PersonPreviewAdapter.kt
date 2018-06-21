package com.handaoui.movies.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.handaoui.movies.Config
import com.handaoui.movies.data.Movie
import com.handaoui.movies.R
import com.handaoui.movies.activities.MovieDetailsActivity
import com.handaoui.movies.data.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.util.ArrayList


class PersonPreviewAdapter(var context: Context,
                           private var personsList: ArrayList<Person>) :
        RecyclerView.Adapter<PersonPreviewAdapter.CustomViewHolder>() {

    override fun getItemCount() = personsList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val person: Person = personsList[position]
        Picasso.with(context)
                .load(Config.imagePreviewUrl + person.profile_path)
                .resize(200,200)
                .centerCrop()
                .into(holder?.imageView)
        holder?.nameView?.text = person.name
        holder?.id = person.id
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.person_preview, parent, false)
        return CustomViewHolder(view)
    }

    fun addToList(persons:ArrayList<Person>){
        personsList.addAll(persons)
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        var nameView: TextView = view.findViewById(R.id.personNameTxt)
        var imageView: ImageView = view.findViewById(R.id.personImg)
        private var cardView: CardView = view.findViewById(R.id.person_card_view)

        init {
            cardView.setOnClickListener {
//                val movieDetailsIntent = Intent(context, MovieDetailsActivity::class.java).apply {
//                    putExtra("id", id)
//                }
//                context.startActivity(movieDetailsIntent)
            }
        }
    }
}