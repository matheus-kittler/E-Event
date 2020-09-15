package com.example.e_event.view.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_event.R
import com.example.e_event.model.Event
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val viewModelDetail: DetailViewModel by viewModels()
//    private val adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        var detail: Event = intent.getSerializableExtra("detail") as Event //TA NULL
        tvTitle.setText(detail.title)
        tvDescription.setText(detail.description)
        tvDate.setText(detail.date)
        tvPrice.setText(detail.price.toString())
        val requestOpitons: RequestOptions by lazy {
            RequestOptions()
                .error(R.drawable.ic_error_image)
                .transform(CenterCrop())

        }
        Glide.with(this)
            .load(detail.image)
            .apply(requestOpitons)
            .thumbnail(0.5f)
            .into(ivPhoto)
    }
}