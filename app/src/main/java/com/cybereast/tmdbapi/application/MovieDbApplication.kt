package com.cybereast.tmdbapi.application

import android.app.Application
import android.content.Context


class MovieDbApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = this

    }

    companion object {
        private lateinit var mContext: Context
        fun getContext(): Context {
            return mContext
        }


    }


}