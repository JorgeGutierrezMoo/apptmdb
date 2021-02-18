package com.example.moviesk.domain

import com.example.moviesk.data.DataSource
import com.example.moviesk.data.model.Movie
import com.example.moviesk.data.model.MovieDetail
import com.example.moviesk.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getMoviesList(index: Int): Resource<List<Movie>> {
        return dataSource.getNowPlayingMoviesDataSource(index)
    }

    override suspend fun getMovieDetail(movie_id: String): Resource<MovieDetail> {
        return dataSource.getMovieDetailsByIdDataSource(movie_id)
    }
}