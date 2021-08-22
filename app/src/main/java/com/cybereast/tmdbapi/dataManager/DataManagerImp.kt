package com.cybereast.tmdbapi.dataManager

import androidx.lifecycle.LiveData
import com.cybereast.tmdbapi.models.Movie
import com.cybereast.tmdbapi.models.ResponseModel
import io.reactivex.Observable
import retrofit2.Response


interface DataManagerImp {
    //Remote calls
    fun getMovieList(page: Int): Observable<Response<ResponseModel>>
    fun getMoviesFromDb(): LiveData<List<Movie>>
    suspend fun insertMovies(movies: ArrayList<Movie>)

}