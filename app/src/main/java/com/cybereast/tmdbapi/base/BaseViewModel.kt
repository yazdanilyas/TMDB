package com.cybereast.tmdbapi.base

import androidx.lifecycle.ViewModel
import com.cybereast.tmdbapi.dataManager.DataManager


abstract class BaseViewModel : ViewModel() {
    val mDataManager = DataManager
}