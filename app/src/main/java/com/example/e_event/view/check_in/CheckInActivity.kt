package com.example.e_event.view.check_in

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityCheckInBinding
import com.example.e_event.util.showAlert
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInActivity : AppCompatActivity() {

    private val checkInViewModel: CheckInViewModel by viewModel()
    private lateinit var binding: ActivityCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_check_in)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupObserves()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val eventId: Int? = intent.getSerializableExtra("eventId") as Int?
        intent.putExtra("eventId", eventId)
        finish()
    }

    private fun setupObserves() {

        checkInViewModel.apply {

//            checkInViewModel.setCheckIn(1, "Matheus", "matheus@gmail.com")

            checkIn.observe(this@CheckInActivity) {
                checkInViewModel.setCheckIn(1, "Matheus", "matheus@gmail.com")
            }

            isError.observe(this@CheckInActivity) {
                if (it != null) {
                    showAlert(
                        getString(R.string.title_error),
                        getString(R.string.error)
                    ) {
                        setNeutralButton("OK", null)
                    }
                }
            }

            isLoading.observe(this@CheckInActivity) {

            }
        }
    }
}