package com.cybereast.tmdbapi.base

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


abstract class BaseFragment : Fragment() {
    companion object {
        val TAG = BaseFragment::class.java.name
    }

    protected lateinit var mActivityListener: Any

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mActivityListener = context
        } catch (ex: ClassCastException) {
            Log.e(TAG, "Unable to cast listener ", ex)
        }
    }

    protected open fun replaceFragment(containerViewId: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(containerViewId, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()
    }

    protected fun setUpActionBar(toolBar: Toolbar, title: String, showBack: Boolean) {
        try {
            (activity as AppCompatActivity).setSupportActionBar(toolBar)
            val actionBar = (activity as AppCompatActivity).supportActionBar
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(showBack)
                actionBar.title = title
                actionBar.setDisplayShowTitleEnabled(true)
                actionBar.setDisplayShowTitleEnabled(true)
                //  actionBar.setHomeAsUpIndicator(R.drawable.ic_back_img)
            }
        } catch (e: NullPointerException) {
            Log.e(TAG, "setUpActionBar: Failed to setup Action bar", e)
        }
    }

    protected fun setUpTransparentActionBar(toolBar: Toolbar, title: String, showBack: Boolean) {
        try {
            (activity as AppCompatActivity).setSupportActionBar(toolBar)
            val actionBar = (activity as AppCompatActivity).supportActionBar
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(showBack)
                actionBar.title = title
                actionBar.setDisplayShowTitleEnabled(true)
                actionBar.setDisplayShowTitleEnabled(true)
                //  actionBar.setHomeAsUpIndicator(R.drawable.ic_back_img_white)
            }
        } catch (e: NullPointerException) {
            Log.e(TAG, "setUpActionBar: Failed to setup Action bar", e)
        }
    }

    protected open fun popUpAllFragmentIncludeThis(pClassName: String?) {
        activity?.supportFragmentManager!!.popBackStack(
            pClassName,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    protected fun setUpTransparentActionBar(
        toolBar: Toolbar,
        title: String,
        showBack: Boolean,
        homeIndicator: Int
    ) {
        try {
            (activity as AppCompatActivity).setSupportActionBar(toolBar)
            val actionBar = (activity as AppCompatActivity).supportActionBar
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(showBack)
                actionBar.title = title
                actionBar.setDisplayShowTitleEnabled(true)
                actionBar.setDisplayShowTitleEnabled(true)
                actionBar.setHomeAsUpIndicator(homeIndicator)
            }
        } catch (e: NullPointerException) {
            Log.e(TAG, "setUpActionBar: Failed to setup Action bar", e)
        }
    }

}