package com.cybereast.tmdbapi.ui.activities.moviesActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cybereast.tmdbapi.R
import com.cybereast.tmdbapi.base.RecyclerViewBaseActivity
import com.cybereast.tmdbapi.constant.Constants
import com.cybereast.tmdbapi.databinding.ActivityMoviesBinding
import com.cybereast.tmdbapi.models.Movie
import com.cybereast.tmdbapi.myData.adapter.RecyclerViewAdapter
import com.cybereast.tmdbapi.myData.enums.NetworkStatus
import com.cybereast.tmdbapi.myData.interfaces.BaseInterface
import com.cybereast.tmdbapi.source.remote.networkViewModel.MovieNetworkViewModel
import com.cybereast.tmdbapi.ui.fragments.MovieDetailFragment
import com.cybereast.tmdbapi.utils.CommonKeys
import com.cybereast.tmdbapi.wrapper.ActivityUtils

class MoviesActivity : RecyclerViewBaseActivity(), BaseInterface, RecyclerViewAdapter.CallBack,
    MoviesImp {
    private lateinit var mAdapter: RecyclerViewAdapter
    private lateinit var mViewModel: MoviesActivityViewModel
    private lateinit var mMovieNetworkViewModel: MovieNetworkViewModel
    private lateinit var mBinding: ActivityMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMoviesBinding.inflate(layoutInflater);
        setContentView(mBinding.root)
        mViewModel = ViewModelProvider(this).get(MoviesActivityViewModel::class.java)
        mMovieNetworkViewModel = ViewModelProvider(this).get(MovieNetworkViewModel::class.java)
        mMovieNetworkViewModel.getMovies(1)
        setObserver()
        setAdapter()

    }

    override fun onPrepareAdapter(): RecyclerView.Adapter<*> {
        return mAdapter;
    }

    override fun showProgressBar() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE

    }

    override fun onViewClicked(view: View, data: Any?) {

    }

    override fun onItemClick(data: Any?, position: Int) {
        val movie = data as Movie
        val bundle = Bundle()
        bundle.putSerializable(CommonKeys.KEY_DATA, movie)
        openMovieDetailFragment(bundle)
        Log.d("TAG", "onItemClick: ")

    }

    override fun onItemLongClick(view: View, data: Any?, position: Int) {

    }

    override fun inflateLayoutFromId(position: Int, data: Any?): Int {
        return R.layout.item_movies
    }

    override fun onNoDataFound() {
        mBinding.textNoDataFound.visibility = View.VISIBLE
    }

    override fun openMovieDetailFragment(pBundle: Bundle) {
        ActivityUtils.launchFragment(this, MovieDetailFragment::class.java.name, pBundle)
    }

    private fun setObserver() {
        mMovieNetworkViewModel.mMovieResponse.observe(this, {
            when (it.status) {
                NetworkStatus.LOADING -> {
                    showProgressBar()
                }
                NetworkStatus.SUCCESS -> {
                    hideProgressBar()
                    val data = (it.t as List<Movie>)
                    if (data != null && data.isNotEmpty()) {
                        data.let { list ->
                            mViewModel.mMoviesList.clear()
                            mViewModel.mMoviesList.addAll(list)
                            mBinding.textNoDataFound.visibility = View.GONE
                        }
                        if (::mAdapter.isInitialized)
                            mAdapter.notifyDataSetChanged()
                    } else {
                        onNoDataFound()
                    }

                }
                NetworkStatus.ERROR -> {
                    hideProgressBar()
                }
                NetworkStatus.COMPLETED -> {
                    hideProgressBar()
                }
                NetworkStatus.EXPIRE -> {
                    hideProgressBar()

                }
            }
        })
    }

    private fun setAdapter() {
        mAdapter = RecyclerViewAdapter(this, mViewModel.mMoviesList)
        setUpGridRecyclerView(
            mBinding.mainCategoriesRecycler,
            Constants.NO_OF_COLUMNS_2,
            resources.getDimensionPixelSize(R.dimen.recycler_categories_vertical_spacing),
            resources.getDimensionPixelSize(R.dimen.recycler_categories_horizontal_spacing)
        )
    }


}