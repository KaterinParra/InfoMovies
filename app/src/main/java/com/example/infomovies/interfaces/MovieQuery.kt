package com.example.infomovies.interfaces

import com.example.infomovies.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieQuery {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey:String) : Call<Response>

}