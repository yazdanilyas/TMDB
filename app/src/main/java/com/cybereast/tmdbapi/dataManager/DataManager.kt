package com.cybereast.tmdbapi.dataManager

import androidx.lifecycle.LiveData
import com.cybereast.tmdbapi.application.MovieDbApplication
import com.cybereast.tmdbapi.constant.Constants
import com.cybereast.tmdbapi.models.Movie
import com.cybereast.tmdbapi.models.ResponseModel
import com.cybereast.tmdbapi.source.local.database.AppRoomDatabase
import com.cybereast.tmdbapi.source.remote.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object DataManager : DataManagerImp {
    private fun getRoomInstance(): AppRoomDatabase {
        return AppRoomDatabase.getDatabaseInstance(MovieDbApplication.getContext())
    }

    override fun getMovieList(page: Int): Observable<Response<ResponseModel>> {
        return ApiService.get().getPopularMovieList(Constants.API_KEY, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMoviesFromDb(): LiveData<List<Movie>> {
        return getRoomInstance().movieDao().getAllData()
    }

    override suspend fun insertMovies(movies: ArrayList<Movie>) {
        getRoomInstance().movieDao().saveData(movies)
    }


}