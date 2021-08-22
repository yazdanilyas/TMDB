package com.cybereast.tmdbapi.ui.activities.moviesActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cybereast.tmdbapi.base.BaseViewModel
import com.cybereast.tmdbapi.models.Movie
import kotlinx.coroutines.launch

class MoviesActivityViewModel : BaseViewModel() {
    var mMoviesList: MutableList<Any> = ArrayList()
    fun getMovieList(): LiveData<List<Movie>> {
        return mDataManager.getMoviesFromDb()
    }

    fun insertMovies(movies: ArrayList<Movie>) {
        viewModelScope.launch {
            mDataManager.insertMovies(movies)

        }
    }
}