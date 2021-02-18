package com.example.moviesk.vo

import com.example.moviesk.domain.WebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val api_key = "a28c4bc831b590dc669ef8a459fdbff7"
    //          /movie/now_playing?api_key=a28c4bc831b590dc669ef8a459fdbff7
    //          /movie/{movie_id}?api_key=a28c4bc831b590dc669ef8a459fdbff7

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

}