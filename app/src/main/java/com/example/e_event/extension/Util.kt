package com.example.e_event.extension

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class Util {

    companion object Util {
        @JvmStatic
        fun formatPrice(text : String) {

        }

        @JvmStatic
        fun getImageSource(context : Context, img : Int) : String =
            "android.resource://" + context + "/" + img

        @JvmStatic
        @BindingAdapter("app:context", "app:src")
        fun setImage(imageView: ImageView, context: Context, image: Int) {

        }
    }
}