package com.example.movieapplication.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.api.model.response.Result
import com.example.movieapplication.MovieApplication
import com.example.movieapplication.R
import com.example.movieapplication.databinding.ItemMovieListBinding
import javax.inject.Inject

class MovieListAdapter @Inject constructor(private var movieListResponse: List<Result>) :
    RecyclerView.Adapter<MovieListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_list,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int) = movieListResponse[position]

    override fun getItemCount() = movieListResponse.size

    class Holder(private var itemBinding: ItemMovieListBinding?) : RecyclerView.ViewHolder(itemBinding?.root as View) {
        fun bind(item: Result) {
            itemBinding?.movieItem = item
        }
    }
}

