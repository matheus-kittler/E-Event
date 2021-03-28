package com.example.e_event.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.location.Geocoder
import android.provider.Settings
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.e_event.R
import java.util.*
import javax.sql.DataSource
import kotlin.coroutines.coroutineContext

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageFromUrl")
    fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl)
                .error(R.drawable.ic_error_image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(view)
        }
    }

//    @JvmStatic
//    @BindingAdapter("locationEvent")
//    fun bindLocation(lat: Double, lng: Double, context: Context): String {
//        val mGeocoder = Geocoder(context)
//        val address = mGeocoder.getFromLocation(lat, lng, 1)
//        return address[0].getAddressLine(0)
//    }

}