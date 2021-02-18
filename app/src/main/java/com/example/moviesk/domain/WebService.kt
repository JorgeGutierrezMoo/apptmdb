package com.example.moviesk.domain

import com.example.moviesk.data.model.MovieDetail
import com.example.moviesk.data.model.MovieResultsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//    https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false

interface WebService {

    //Esto es para buscar películas de acuerdo al nombre,
    //En este ejemplo buscamos la película Justice League
    // Se puede descomentar para la búsqueda
    //@GET("/search/movie?api_key=a28c4bc831b590dc669ef8a459fdbff7&query=")
    //suspend fun getMoviesByName(@Query("movieName") movieName: String)

//    @GET("movie/now_playing?api_key=a28c4bc831b590dc669ef8a459fdbff7&language=es-MX")
//    suspend fun getNowPlayingMovies(): MovieResultsList

    @GET("movie/now_playing?api_key=a28c4bc831b590dc669ef8a459fdbff7&language=es-MX&page=")
    suspend fun getNowPlayingMovies(
        @Query("page") page:Int
    ): MovieResultsList

    @GET("movie/{movie_id}?api_key=a28c4bc831b590dc669ef8a459fdbff7&language=es-MX")
    suspend fun getMovieDetailsById(@Path("movie_id") movie_id: String): MovieDetail

}