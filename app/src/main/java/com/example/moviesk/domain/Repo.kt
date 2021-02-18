package com.example.moviesk.domain

import com.example.moviesk.data.model.Movie
import com.example.moviesk.data.model.MovieDetail
import com.example.moviesk.vo.Resource

interface Repo {
    suspend fun getMoviesList(page_index: Int): Resource<List<Movie>>
    suspend fun getMovieDetail(movie_id: String): Resource<MovieDetail>
}