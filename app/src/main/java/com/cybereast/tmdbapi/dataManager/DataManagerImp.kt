package com.cybereast.tmdbapi.dataManager

import com.cybereast.tmdbapi.models.ResponseModel
import io.reactivex.Observable
import retrofit2.Response


interface DataManagerImp {
    //Remote calls
    fun getMovieList(page: Int): Observable<Response<ResponseModel>>

}