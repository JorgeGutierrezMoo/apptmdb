package com.example.moviesk.ui.viewmodel

import androidx.lifecycle.*
import com.example.moviesk.Coroutines
import com.example.moviesk.data.model.Movie
import com.example.moviesk.data.model.MovieDetail
import com.example.moviesk.domain.Repo
import com.example.moviesk.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainViewModel(private val repo: Repo): ViewModel() {
    var pageIndexMovie: Int = 1
    private lateinit var job:Job
    private var _fetchMoviesList = MutableLiveData<Resource<List<Movie>>>()
    val fetchMoviesList:LiveData<Resource<List<Movie>>>
    get() = _fetchMoviesList

    private val movieIdData = MutableLiveData<String>()
    init {
        refreshMovieList()
    }
    fun setMovieIdData(movie_id: String){
        movieIdData.value = movie_id
    }
//------------------------------------------------------------------------------
    fun refreshMovieList() {
        job = Coroutines.ioThenMain({
            repo.getMoviesList(pageIndexMovie)
        },{
            _fetchMoviesList.value = it
        })
    }

    val fetchMovieDetail = movieIdData.distinctUntilChanged().switchMap { movieId ->
        liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getMovieDetail(movieId))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    } }
}