package com.example.infomovies.models

data class Response(
    var status_code: Int = 0,
    var status_message: String,
    var page: Int,
    var results: ArrayList<Movie>,
    var total_pages: Int,
    var total_results: Int
)