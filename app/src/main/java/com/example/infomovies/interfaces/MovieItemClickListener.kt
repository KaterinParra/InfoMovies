package com.example.infomovies.interfaces

import com.example.infomovies.models.Movie

interface MovieItemClickListener {
    fun onItemClicked(detailMovie:Movie)
}