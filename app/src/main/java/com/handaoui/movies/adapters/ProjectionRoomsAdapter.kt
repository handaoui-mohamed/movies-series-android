package com.handaoui.movies.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
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
import com.handaoui.movies.data.ProjectionRoom
import com.handaoui.movies.fakers.User
import java.util.ArrayList


class ProjectionRoomsAdapter(var context: Context,
                           private var projectionRoomsList: ArrayList<ProjectionRoom>) :
        RecyclerView.Adapter<ProjectionRoomsAdapter.CustomViewHolder>() {

    override fun getItemCount() = projectionRoomsList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val projectionRoom = projectionRoomsList[position]
        if(holder != null){
            holder.imageView.setImageResource(projectionRoom.image)
            holder.nameView.text = projectionRoom.name
            holder.addressView.text = projectionRoom.address
            holder.id = projectionRoom.id
            if(User.profile.isProjectionRoomFavored(holder.id)){
                holder.favoriteBtn.setImageResource(R.drawable.zzz_heart)
            }else{
                holder.favoriteBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.projection_room_card, parent, false)
        return CustomViewHolder(view)
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        var nameView: TextView = view.findViewById(R.id.nameTxt)
        var addressView: TextView = view.findViewById(R.id.addressTxt)
        var imageView: ImageView = view.findViewById(R.id.imageView)
        var favoriteBtn: ImageView = view.findViewById(R.id.favoriteBtn)
        private var cardView: CardView = view.findViewById(R.id.card_view)

        init {
            cardView.setOnClickListener {
                val map = "http://maps.google.co.in/maps?q=" + addressView.text
                val googleMap = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                context.startActivity(googleMap)
            }

            favoriteBtn.setOnClickListener{
                if(User.profile.isProjectionRoomFavored(id)){
                    favoriteBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    User.profile.removeProjectionRoom(id)
                }else{
                    favoriteBtn.setImageResource(R.drawable.zzz_heart)
                    User.profile.addProjectionRoom(id)
                }
            }
        }
    }
}