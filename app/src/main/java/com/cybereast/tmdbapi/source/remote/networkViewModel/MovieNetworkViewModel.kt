package com.cybereast.tmdbapi.source.remote.networkViewModel

import androidx.lifecycle.MutableLiveData
import com.cybereast.tmdbapi.base.BaseViewModel
import com.cybereast.tmdbapi.models.ApiResponse
import com.cybereast.tmdbapi.models.BindingAdapter.mDataManager
import com.cybereast.tmdbapi.models.ErrorResponse
import com.cybereast.tmdbapi.models.ResponseModel
import com.cybereast.tmdbapi.source.remote.rxjava.CustomError
import com.cybereast.tmdbapi.source.remote.rxjava.CustomObserver
import retrofit2.Response

class MovieNetworkViewModel : BaseViewModel() {
    var mMovieResponse: MutableLiveData<ApiResponse> = MutableLiveData()

    var movieResponse: MutableLiveData<ApiResponse>
        get() = mMovieResponse
        set(value) {
            mMovieResponse = value
        }

    fun getMovies(page: Int) {
        mDataManager.getMovieList(page).doOnSubscribe {
            mMovieResponse.value = ApiResponse.loading()
        }.subscribe(object : CustomObserver<Response<ResponseModel>>() {
            override fun onSuccess(t: Response<ResponseModel>) {
                when {
                    t.isSuccessful -> {
                        mMovieResponse.value = ApiResponse.success(t.body()?.movieResults)
                    }

                }
            }

            override fun onError(e: Throwable, isInternetError: Boolean, error: CustomError?) {
                mMovieResponse.value = ApiResponse.error(
                    error?.code?.let {
                        ErrorResponse(
                            false,
                            error.message.toString(),
                            it
                        )
                    }
                )
            }

            override fun onRequestComplete() {
                //  mCategoryResponse.value = ApiResponse.complete()
            }
        })
    }
}