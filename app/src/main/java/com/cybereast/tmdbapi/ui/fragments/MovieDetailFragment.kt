package com.cybereast.tmdbapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cybereast.tmdbapi.base.BaseFragment
import com.cybereast.tmdbapi.databinding.MovieDetailFragmentBinding
import com.cybereast.tmdbapi.models.Movie
import com.cybereast.tmdbapi.utils.CommonKeys

class MovieDetailFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
        fun newInstance(pBundle: Bundle) = MovieDetailFragment().apply {
            arguments = pBundle
        }
    }

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var mBinding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        setUpActionBar()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        getBundleData()
        setMovieDetail()
    }

    private fun setMovieDetail() {
        mBinding.obj = viewModel.mMovie
        mBinding.executePendingBindings()
    }

    private fun getBundleData() {
        arguments.let { bundle ->
            viewModel.mMovie = bundle?.getSerializable(CommonKeys.KEY_DATA) as Movie
        }
    }

   private fun setUpActionBar() {
       setUpActionBar(mBinding.activityToolbar.toolbar, "Movie Detail", true)
   }

}