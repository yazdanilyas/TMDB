package com.cybereast.tmdbapi.source.remote.retrofit

import android.annotation.SuppressLint
import android.util.Log
import com.cybereast.tmdbapi.application.MovieDbApplication
import com.cybereast.tmdbapi.source.local.prefrance.PrefUtils
import com.cybereast.tmdbapi.utils.CommonKeys
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.io.IOException

class DataInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        var requestLog = String.format(
            "Sending request %s on %s%n%s",
            request.url(), chain.connection(), request.headers()
        )
        if (request.method().compareTo("post", ignoreCase = true) == 0) {
            requestLog = "\n" + requestLog + "\n" + bodyToString(request)
        }
        Log.i(TAG, "request\n$requestLog")
        val response: Response
        val requestBuilder = request.newBuilder()
//            .header("api_key", getApiKey())
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
        if (PrefUtils.getString(MovieDbApplication.getContext(), CommonKeys.KEY_JWT) != null) {
            val jwt = "Bearer " + PrefUtils.getString(
                MovieDbApplication.getContext(),
                CommonKeys.KEY_JWT
            )
            requestBuilder.header("Authorization", jwt)
            Log.e(TAG, "header : ${requestBuilder.toString()}")
            val request2 = requestBuilder.build()
            response = chain.proceed(request2)
            Log.d(TAG, "intercept: with Token ".plus(jwt))
        } else {
            Log.d(TAG, "intercept: with out token ")
            val request2 = requestBuilder.build()
            response = chain.proceed(request2)
        }
        val t2 = System.nanoTime()
        @SuppressLint("DefaultLocale") val responseLog = String.format(
            "Received response for %s in %.1fms%n%s",
            response.request().url(), (t2 - t1) / 1e6, response.headers()
        )
        assert(response.body() != null)
        val bodyString = response.body()!!.string()
        Log.i(TAG, "response\n$responseLog\n$bodyString")
        return response.newBuilder()
            .body(ResponseBody.create(response.body()!!.contentType(), bodyString))
            .build()
    }

    private fun getApiKey(): String {
//        return when (BuildConfig.FLAVOR) {
//            Constants.STAGING -> {
//                Constants.STAGING_API_KEY
//            }
//            Constants.DEVELOPMENT -> {
//                Constants.DEVELOPMENT_API_KEY
//            }
//            Constants.ACCEPTANCE -> {
//                Constants.ACCEPTANCE_API_KEY
//            }
//            else -> {
//                Constants.PRODUCTION_API_KEY
//            }
//        }
        return "";
    }

    companion object {
        private const val TAG = "DataInterceptor"
        private fun bodyToString(request: Request): String {
            return try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                copy.body()?.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: Exception) {
                "did not work"
            }
        }
    }
}