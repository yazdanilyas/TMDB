package com.cybereast.tmdbapi.wrapper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cybereast.tmdbapi.utils.CommonKeys
import com.cybereast.tmdbapi.utils.CommonKeys.Companion.KEY_FRAGMENT


object ActivityUtils {

    fun launchFragment(context: Context, pClassName: String) {
        val profileFragment = Intent(context, FrameActivity::class.java)
        profileFragment.putExtra(KEY_FRAGMENT, pClassName)
        context.startActivity(profileFragment)
    }


    fun launchFragment(context: Context, pClassName: String, pBundle: Bundle) {
        val intent = Intent(context, FrameActivity::class.java)
        intent.putExtra(KEY_FRAGMENT, pClassName)
        intent.putExtra(CommonKeys.KEY_DATA, pBundle)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(intent)
    }

    fun startNewActivity(activity: Activity, pClassName: Class<*>) {
        val intent = Intent(activity, pClassName)
        activity.startActivity(intent)
    }

    fun startNewActivity(activity: Activity, pClassName: Class<*>, bundle: Bundle) {
        val intent = Intent(activity, pClassName)
        intent.putExtra(CommonKeys.KEY_DATA, bundle)
        activity.startActivity(intent)
    }

    fun startNewActivity(activity: Activity, pClassName: Class<*>, flag: Int) {
        val intent = Intent(activity, pClassName)
        intent.addFlags(flag)
        activity.startActivity(intent)
    }

    fun startNewActivity(
        activity: Activity,
        pClassName: Class<*>,
        bundle: Bundle,
        vararg flag: Int
    ) {
        val intent = Intent(activity, pClassName)
        intent.putExtra(CommonKeys.KEY_DATA, bundle)
        for (f in flag)
            intent.addFlags(f)
        activity.startActivity(intent)
    }

}
