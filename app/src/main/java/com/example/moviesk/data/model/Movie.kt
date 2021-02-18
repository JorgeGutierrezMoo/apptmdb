package com.example.moviesk.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("backdrop_path")
    val imagen: String = "",
    @SerializedName("title")
    val nombre: String = "",
    @SerializedName("release_date")
    val festreno: String = "",
    @SerializedName("vote_average")
    val rating: String = ""
) : Parcelable

@Parcelize
data class MovieResultsList(
    @SerializedName("results")
    val movieResultsList: List<Movie>
) : Parcelable
//---------------------------
@Parcelize
data class MovieDetail(
    @SerializedName("id")
    val idDetail : String = "",
    @SerializedName("backdrop_path")
    val imagenDetail : String = "",
    @SerializedName("title")
    val nombreDetail : String = "",
    @SerializedName("runtime")
    val duracionDetail : String = "",
    @SerializedName("release_date")
    val festrenoDetail : String = "",
    @SerializedName("vote_average")
    val ratingDetail : String = "",
    @SerializedName("genres")
    val genresDetailList: List<Genre>,
    @SerializedName("overview")
    val descripcionDetail : String = ""
) : Parcelable

@Parcelize
data class Genre(
    @SerializedName("id")
    val genreIdElement: String = "",
    @SerializedName("name")
    val genreNameElement: String = ""
) : Parcelable
//
