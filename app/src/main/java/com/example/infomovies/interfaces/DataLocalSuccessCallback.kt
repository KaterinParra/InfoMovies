package com.example.infomovies.interfaces

import com.example.infomovies.models.Movie

interface DataLocalSuccessCallback {
    fun onSuccess(array: ArrayList<Movie>)
}