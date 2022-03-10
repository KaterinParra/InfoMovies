package com.example.infomovies.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infomovies.models.Movie

@Dao
interface MovieDAO {
    @Query("select * from Movie")
    fun getAll() : Array<Movie>

    @Insert
    fun insertMovie(vararg movies: Movie)
}