package com.cybereast.tmdbapi.models

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.cybereast.tmdbapi.R
import com.cybereast.tmdbapi.dataManager.DataManager
import com.cybereast.tmdbapi.utils.loadImg
import com.google.android.material.imageview.ShapeableImageView


object BindingAdapter {
    val mDataManager = DataManager

    @JvmStatic
    @BindingAdapter("setTextToTextView")
    fun setTextToTextView(textView: TextView, text: String?) {
        textView.text = text
    }

    @JvmStatic
    @BindingAdapter("setImageViewImage")
    fun setImageViewImage(pImageView: ImageView?, image: String) {
        pImageView?.loadImg("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + image)
    }

    @JvmStatic
    @BindingAdapter("setImageViewProfile")
    fun setImageViewProfile(pImageView: ImageView?, imageUrl: String) {
        pImageView?.loadImg(imageUrl, R.drawable.profile_placeholder)
    }


    @JvmStatic
    @BindingAdapter("setShapeableImage")
    fun setShapeableImage(pImageView: ShapeableImageView?, image: String) {
        pImageView?.loadImg(image)
    }

    @JvmStatic
    @BindingAdapter("setProductDollarPrice")
    fun setProductDollarPrice(pTextView: TextView?, pPrice: Double) {
        pTextView?.text = "$".plus(String.format("%.2f", pPrice))
    }


    @JvmStatic
    @BindingAdapter("setStrikeThroughText")
    fun setStrikeThroughText(textView: AppCompatTextView, text: String) {
        textView.text = text
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }


    @JvmStatic
    @BindingAdapter("setPriceWithDollarAndStrike")
    fun setPriceWithDollarAndStrike(pTextView: TextView?, pPrice: Double) {
        if (pTextView != null) {
            pTextView.text = "$".plus(String.format("%.2f", pPrice))
            pTextView.paintFlags = pTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }


}