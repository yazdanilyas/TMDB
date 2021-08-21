package com.cybereast.tmdbapi.models

import com.google.gson.annotations.SerializedName

data class ResponseModel(

//    @SerializedName("page")
//    var page: Int,
//    @SerializedName("total_pages")
//    var status: Int,
//    @SerializedName("total_results")
//    var message: Int,
    @SerializedName("results")
    var movieResults: List<Movie>
)