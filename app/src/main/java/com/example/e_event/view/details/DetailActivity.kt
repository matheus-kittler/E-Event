package com.example.e_event.view.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_event.R
import com.example.e_event.adapter.DetailAdapter
import com.example.e_event.model.Event
import com.example.e_event.view.map.MapsActivity
import com.example.e_event.view.check_in.CheckInActivity
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.row_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private val viewModelDetail: DetailViewModel by viewModels()
    private val adapter: DetailAdapter by lazy {
        DetailAdapter(this)
    }
    private val DATE_FORMAT = "dd/MM/yyyy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        var detail: Event = intent.getSerializableExtra("detail") as Event
        tvTitle.setText(detail.title)
        tvDescription.setText(detail.description)
        tvDate.text = SimpleDateFormat(DATE_FORMAT, Locale.US).format(Date(detail.date!!))
        tvPrice.text = detail.price.toString().replace(".", ",").replaceAfter("0", "0")
        adapter.peoples = detail.people

        rvPeople.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.VERTICAL, false)
            adapter = this@DetailActivity.adapter
        }

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

//        ibCheckIn.setOnClickListener {
//
//            val intent = Intent(this@DetailActivity, CheckInActivity::class.java)
//            intent.putExtra("detail", detail)
//            setResult(Activity.RESULT_OK, intent)
//            startActivity(intent)
//        }

//        ibShare.setOnClickListener {
//            val location: String = detail.latitude.toString()
//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND
//            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, location)
//            intent.type = "text/plain"
//
//            startActivity(Intent.createChooser(intent, "Compartilhe este evento : "))
//        }


        ivPhoto.setOnClickListener {
            val event: Event = detail
            val intent = Intent(this@DetailActivity, MapsActivity::class.java)
            intent.putExtra("location", event)
            startActivity(intent)
        }

    }
}