package com.example.gonzalezpauandroidstact19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById<TextView>(R.id.txtTitle)
        val body: TextView = v.findViewById<TextView>(R.id.txtBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = posts[position]
        holder.title.text = p.title
        holder.body.text = p.body
    }

    override fun getItemCount() = posts.size
}