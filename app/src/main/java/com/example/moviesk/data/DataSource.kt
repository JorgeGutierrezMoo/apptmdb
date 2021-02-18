package com.example.moviesk.data

import com.example.moviesk.data.model.Movie
import com.example.moviesk.data.model.MovieDetail
import com.example.moviesk.vo.Resource
import com.example.moviesk.vo.RetrofitClient

class DataSource {

    suspend fun getNowPlayingMoviesDataSource(page_index: Int): Resource<List<Movie>> {
        return Resource.Success(RetrofitClient.webservice.getNowPlayingMovies(page_index).movieResultsList)
    }

    suspend fun getMovieDetailsByIdDataSource(movie_id: String): Resource<MovieDetail> {
        return Resource.Success(RetrofitClient.webservice.getMovieDetailsById(movie_id))
    }
}