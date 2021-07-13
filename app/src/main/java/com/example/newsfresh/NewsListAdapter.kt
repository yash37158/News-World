package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()
//    Create viewholder and con         vert xml to view type.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition ])
        }
        return viewHolder
    }
// To Bind the item to the data
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
    holder.author.text = currentItem.author
    Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }
// Numbers of item present
    override fun getItemCount(): Int {
        return items.size
    }

//    providing news item to adapter
    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)

    notifyDataSetChanged()
    }
}

class NewsViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
    
}