package com.cybereast.tmdbapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("status")
    @Expose
    val status: Boolean,
    @SerializedName("message")
    @Expose
    val message: String,
    @SerializedName("code")
    @Expose
    val code: Int
)