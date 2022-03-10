package com.example.infomovies.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infomovies.interfaces.MovieDAO

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDAO() : MovieDAO
}