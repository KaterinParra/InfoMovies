package com.example.infomovies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.infomovies.R
import com.example.infomovies.utils.Constants
import com.example.infomovies.databinding.MovieItemBinding
import com.example.infomovies.interfaces.MovieItemClickListener
import com.example.infomovies.models.Movie
import com.squareup.picasso.Picasso

class MovieItemAdapter : Adapter<MovieItemAdapter.ViewHolder>() {

    var dataItem: ArrayList<Movie> = ArrayList()
    lateinit var listener: MovieItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataItem[position]
        Picasso.get().load(Constants.IMAGE_MAIN_PATH + item.poster_path)
            .into(holder.binding.imageItem)
        holder.binding.movieTitleItem.text = item.title
        holder.binding.movieDescItem.text = item.overview
        holder.binding.itemMovie.setOnClickListener {
            listener.onItemClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = MovieItemBinding.bind(itemView)
    }


}