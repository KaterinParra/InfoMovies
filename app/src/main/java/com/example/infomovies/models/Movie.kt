package com.example.infomovies.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Movie(@PrimaryKey var id:Int,
                 @ColumnInfo var original_language:String,
                 @ColumnInfo var original_title:String,
                 @ColumnInfo var overview:String,
                 @ColumnInfo var popularity:Double,
                 @ColumnInfo var poster_path:String,
                 @ColumnInfo var release_date:String,
                 @ColumnInfo var title:String,
                 @ColumnInfo var vote_average:Double,
                 @ColumnInfo var vote_count:Int) : Serializable