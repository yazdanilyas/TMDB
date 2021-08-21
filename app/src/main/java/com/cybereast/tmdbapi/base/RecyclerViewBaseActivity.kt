package com.cybereast.tmdbapi.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cybereast.tmdbapi.myData.adapter.VerticalSpaceItemDecoration

abstract class RecyclerViewBaseActivity : BaseActivity() {

    protected open fun setUpRecyclerView(pRecyclerView: RecyclerView, orientation: Int) {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = orientation
        pRecyclerView.layoutManager = linearLayoutManager
        pRecyclerView.itemAnimator = DefaultItemAnimator()
        val mAdapter: RecyclerView.Adapter<*> = onPrepareAdapter()
        pRecyclerView.adapter = mAdapter
    }

    protected open fun setUpRecyclerView(pRecyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        pRecyclerView.layoutManager = linearLayoutManager
        pRecyclerView.itemAnimator = DefaultItemAnimator()
        val mAdapter: RecyclerView.Adapter<*> = onPrepareAdapter()
        pRecyclerView.adapter = mAdapter
    }


    protected open fun setUpHorizontalRecyclerView(
        pRecyclerView: RecyclerView,
        horizentalSpacing: Int
    ) {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        pRecyclerView.layoutManager = linearLayoutManager
        val mItemDecorator = VerticalSpaceItemDecoration()
        mItemDecorator.HorizentalSpaceItemDecoration(horizentalSpacing)
        pRecyclerView.addItemDecoration(mItemDecorator)
        pRecyclerView.itemAnimator = DefaultItemAnimator()
        val mAdapter: RecyclerView.Adapter<*> = onPrepareAdapter()
        pRecyclerView.adapter = mAdapter
    }

    protected open fun setUpGridRecyclerView(
        pRecyclerView: RecyclerView,
        numColums: Int,
        verticalSpacing: Int,
        horizentalSpacing: Int
    ) {

        pRecyclerView.layoutManager = GridLayoutManager(this, numColums)
        pRecyclerView.itemAnimator = DefaultItemAnimator()

        pRecyclerView.clipToPadding = false
        pRecyclerView.clipChildren = false
        pRecyclerView.setHasFixedSize(true)
        if (pRecyclerView.itemDecorationCount <= 0) {
            pRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(
                        horizentalSpacing,
                        verticalSpacing,
                        horizentalSpacing,
                        verticalSpacing,
                    )
                }
            })
        }
        if (pRecyclerView.adapter == null) {
            pRecyclerView.adapter = onPrepareAdapter()
        } else {
            pRecyclerView.adapter = null
            pRecyclerView.adapter = onPrepareAdapter()
        }
    }

    protected abstract fun onPrepareAdapter(): RecyclerView.Adapter<*>

}