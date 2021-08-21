package com.cybereast.tmdbapi.dataManager

import com.cybereast.tmdbapi.constant.Constants
import com.cybereast.tmdbapi.models.ResponseModel
import com.cybereast.tmdbapi.source.remote.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object DataManager : DataManagerImp {
    override fun getMovieList(page: Int): Observable<Response<ResponseModel>> {
        return ApiService.get().getPopularMovieList(Constants.API_KEY, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}