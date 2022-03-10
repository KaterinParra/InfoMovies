package com.example.infomovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.infomovies.utils.Constants
import com.example.infomovies.databinding.ActivityMovieDetailBinding
import com.example.infomovies.models.Movie
import com.squareup.picasso.Picasso

class MovieDetail : AppCompatActivity() {
    lateinit var binding : ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie : Movie = intent.getSerializableExtra("movie") as Movie

        Picasso.get().load(Constants.IMAGE_MAIN_PATH+movie.poster_path).into(binding.imageMovie)
        binding.title.text = movie.title
        binding.desc.text = movie.overview
        binding.originalLanguage.text = String.format(getString(R.string.original_language), movie.original_language)
        binding.originalTitle.text = String.format(getString(R.string.original_title), movie.original_title)
        binding.popularity.text = String.format(getString(R.string.popularity), movie.popularity)
        binding.releaseDate.text = String.format(getString(R.string.release_date), movie.release_date)
        binding.votes.text = String.format(getString(R.string.votes), movie.vote_average)

    }
}