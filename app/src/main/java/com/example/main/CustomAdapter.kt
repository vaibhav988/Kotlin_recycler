package com.example.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Articles
import com.squareup.picasso.Picasso

class CustomAdapter(private val mList: List<Articles> , private  val context:Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private val picasso = Picasso.get()
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)

        return ViewHolder(view)
    }
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val article : Articles = mList.get(position)
        holder.descview.setText(article.description)
        holder.publishview.setText(article.publishedAt)
        holder.titleview.setText(article.title)
        picasso.load(article.urlToImage).placeholder(R.drawable.img).into(holder.imageView)
        holder.itemView.setOnClickListener(
            View.OnClickListener {

                var intent = Intent( context , web_view::class.java )
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url" ,article.url)

                context.startActivity(intent)

        })
    }

    // return the number of the items in the list

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.news_image)
        val titleview: TextView = itemView.findViewById(R.id.title)
        val descview : TextView = itemView.findViewById(R.id.description)
        val publishview : TextView = itemView.findViewById(R.id.published_at)

    }
}
