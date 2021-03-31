package com.example.e_event.view.details

import android.content.Intent
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
        eventId = intent.getSerializableExtra("eventId") as Int?
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

        eventId = intent.getSerializableExtra("eventId") as Int?

        detailViewModel.apply {

            eventId?.let {
                getDetails(it)
            }

            event.observe(this@DetailActivity) { event ->

                val save: Event? = event

                if (save != null) {
                    binding.tvAddressEvent.apply {
                        save.latitude?.let { lat ->
                            save.longitude?.let { lng ->
                                text = getLocation(lat, lng, this@DetailActivity)
                            }
                        }
                    }

                    binding.ibCheckIn.setOnClickListener {
                        val intent = Intent(this@DetailActivity, CheckInActivity::class.java)
                        intent.putExtra("id", save.id)
                        startActivity(intent)
                    }

                    binding.ibShare.setOnClickListener {
                        var message: String = ""
                        //TODO arrumar isso se der tempo
                        message += "\nEvento: " + binding.tvTitle.text
                        message += "\nData: " + binding.tvDate.text
                        message += "\nIngresso: " + binding.tvPrice.text
                        message += "\nLocal: " + binding.tvAddressEvent.text

                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, message)
                        intent.type = "text/plain"

                        startActivity(intent)
                    }
                }
            }

            isError.observe(this@DetailActivity) {
                if (it != null) {
                    showAlert(
                        getString(R.string.title_error),
                        getString(R.string.error)
                    ) {
                        setPositiveButton("OK") { _, _ ->
                            finish()
                        }
                    }
                }
            }

            isLoading.observe(this@DetailActivity) {

            }
        }
    }
}