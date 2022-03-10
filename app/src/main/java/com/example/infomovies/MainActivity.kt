package com.example.infomovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infomovies.adapters.MovieItemAdapter
import com.example.infomovies.databinding.ActivityMainBinding
import com.example.infomovies.interfaces.MovieItemClickListener
import com.example.infomovies.models.Movie

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var viewModelMovie : ViewModelMovie


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MovieItemAdapter()
        val intent = Intent(this, MovieDetail::class.java)
        viewModelMovie = ViewModelMovie()
        viewModelMovie.binding = binding
        binding.movieList.layoutManager = LinearLayoutManager(this)
        binding.movieList.adapter = adapter
        binding.loader.visibility = View.VISIBLE

        binding.btnErrorMessage.setOnClickListener {
            viewModelMovie.getMoviesFromLocal(this)
        }

        viewModelMovie.movies.observe(this) {
            binding.loader.visibility = View.GONE
            binding.errorMessage.visibility = View.GONE
            binding.btnErrorMessage.visibility = View.GONE
            adapter.dataItem = it

            adapter.notifyDataSetChanged()
        }
        adapter.listener = object:MovieItemClickListener {
            override fun onItemClicked(movie: Movie) {
                intent.putExtra("movie", movie)
                startActivity(intent)
            }
        }
        viewModelMovie.getMovies(this)
    }
}