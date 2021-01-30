package com.example.e_event.view.details

import android.app.AlertDialog
import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_event.R
import com.example.e_event.adapter.DetailAdapter
import com.example.e_event.model.Event
import com.example.e_event.view.check_in.CheckInActivity
import com.example.e_event.view.map.MapsActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var EVENT : Int = 11
    private val viewModelDetail: DetailViewModel by viewModels()
    private val adapter: DetailAdapter by lazy {
        DetailAdapter(this)
    }
    private val DATE_FORMAT = "dd/MM/yyyy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        delayScreen()
        getDataField()

        rvPeople.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.VERTICAL, false)
            adapter = this@DetailActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        delayScreen()
        getDataField()
    }

    override fun onRestart() {
        super.onRestart()
        delayScreen()
        getDataField()
    }

    fun getLocation(lat: Double, lng: Double) : String {
        val mGeocoder = Geocoder(this)
        val address = mGeocoder.getFromLocation(lat, lng, 1)
        return address[0].getAddressLine(0)
    }

    fun delayScreen() {
        Handler().postDelayed({
            ivPhoto.visibility = View.VISIBLE
            pbLoading.visibility = View.GONE
        }, 3000)
    }

    fun getDataField() {
        val detail: Event = intent.getSerializableExtra("detail") as Event
        tvAddressEvent.text = getLocation(detail.latitude!!, detail.longitude!!)
        tvTitle.setText(detail.title)
        tvDescription.setText(detail.description)
        tvDate.text = SimpleDateFormat(DATE_FORMAT, Locale.US).format(Date(detail.date!!))
        tvPrice.text = detail.price.toString().replace(".", ",").replaceAfter("0", "0")
        adapter.peoples = detail.people

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

        ibCheckIn.setOnClickListener {

            val intent = Intent(this@DetailActivity, CheckInActivity::class.java)
            intent.putExtra("detail", detail)
            startActivityForResult(intent, EVENT)

        }

        ibShare.setOnClickListener {
            var message : String = "";
            message += "\nEvento: " + tvTitle.text
            message += "\nData: " + tvDate.text
            message += "\nIngresso: " + tvPrice.text
            message += "\nLocal: " + tvAddressEvent.text
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"

            startActivity(intent)
        }


        tvAddressEvent.setOnClickListener {
            val event: Event = detail
            val intent = Intent(this@DetailActivity, MapsActivity::class.java)
            intent.putExtra("location", event)
            startActivity(intent)
        }
    }

    private fun alertDialogError(error: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setTitle("Erro!")
            .setMessage(error)
            .setNeutralButton("Ok", null).also {
                it.create().show()
            }
    }
}