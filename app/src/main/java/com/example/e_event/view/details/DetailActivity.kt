package com.example.e_event.view.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityDetailBinding
import com.example.e_event.model.Event
import com.example.e_event.util.showAlert
import com.example.e_event.view.check_in.CheckInActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val KEY_ID = "eventId"
private const val EVENT = "\nEvento: "
private const val DATE = "\nData: "
private const val TICKET = "\nIngresso: "
private const val LOCAL = "\nLocal: "

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var eventId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupBinding()
        setupObserves()
    }

    override fun onRestart() {
        super.onRestart()
        eventId = intent.getSerializableExtra(KEY_ID) as Int?
        eventId?.let { detailViewModel.getDetails(it) }
    }

    private fun setupBinding() {
        val activity = this
        binding.apply {
            lifecycleOwner = activity
            detailViewModel = activity.detailViewModel
        }
    }

    private fun setupObserves() {

        eventId = intent.getSerializableExtra(KEY_ID) as Int?

        detailViewModel.apply {

            eventId?.let {
                getDetails(it)
            }

            event.observe(this@DetailActivity) { event ->

                val save: Event = event ?: return@observe

                binding.tvAddressEvent.apply {

                    text = event.latitude.takeIf {
                        event.latitude != null && event.longitude != null
                    }?.let {
                        getLocation(event.latitude!!, event.longitude!!, this@DetailActivity)
                    }.also {
                        setOnClickListener {
                            goGoogleMaps(event.longitude!!, event.latitude!!)
                        }
                    }
                }


                binding.ibCheckIn.setOnClickListener {
                    val intent = Intent(this@DetailActivity, CheckInActivity::class.java)
                    intent.putExtra(KEY_ID, save.id)
                    startActivity(intent)
                }

                binding.ibShare.setOnClickListener {
                    var message: String = ""
                    message += EVENT + binding.tvTitle.text
                    message += DATE + binding.tvDateNumber.text
                    message += TICKET + binding.tvPrice.text
                    message += LOCAL + binding.tvAddressEvent.text

                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, message)
                    intent.type = "text/plain"

                    startActivity(intent)
                }
            }

            isError.observe(this@DetailActivity) {
                if (it != null) {
                    showAlert(
                        getString(R.string.title_error),
                        getString(R.string.error)
                    ) {
                        setPositiveButton(getString(R.string.button_ok)) { _, _ ->
                            finish()
                        }
                    }
                }
            }
        }
    }
    private fun goGoogleMaps(longitude: Double, latitude: Double) {
        val uri = "http://maps.google.com/maps?daddr=$latitude,$longitude (Where the event is at)"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}