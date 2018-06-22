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
import com.handaoui.movies.R
import com.handaoui.movies.activities.PersonDetailsActivity
import com.handaoui.movies.data.Person
import com.squareup.picasso.Picasso
import java.util.ArrayList


class PersonsListAdapter(var context: Context,
                           private var personsList: ArrayList<Person>) :
        RecyclerView.Adapter<PersonsListAdapter.CustomViewHolder>() {

    override fun getItemCount() = personsList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val person: Person = personsList[position]
        Picasso.with(context)
                .load(Config.imagePreviewUrl + person.profile_path)
                .into(holder?.imageView)
        holder?.projectionRoomView?.text = ""
        holder?.titleView?.text = person.name
        holder?.id = person.id
    }

    fun updateList(persons: ArrayList<Person>) {
        personsList = persons
        notifyDataSetChanged()
    }

    fun addToList(persons: ArrayList<Person>) {
        personsList.addAll(persons)
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
                val selectedPerson = personsList.find { person -> person.id == id }
                val personDetailsIntent = Intent(context, PersonDetailsActivity::class.java).apply {
                    if(selectedPerson != null){
                        putExtra("id", id)
                        putExtra("name", selectedPerson.name)
                        putExtra("profile_path", selectedPerson.profile_path)
                    }
                }
                context.startActivity(personDetailsIntent)
            }
        }
    }
}