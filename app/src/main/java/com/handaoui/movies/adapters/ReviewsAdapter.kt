package com.handaoui.movies.adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.handaoui.movies.R

import com.handaoui.movies.data.Comment

class ReviewsAdapter(private val context: Context, private val cartList: MutableList<Comment>) :
        RecyclerView.Adapter<ReviewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var description: TextView = view.findViewById(R.id.description)
        var price: TextView = view.findViewById(R.id.price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = cartList[position]
        holder.name.text = item.author
        holder.description.text = item.content
        holder.price.text = item.rating.toString()
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}