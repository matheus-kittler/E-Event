package com.example.e_event.view.check_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityCheckInBinding
import com.example.e_event.model.User
import com.example.e_event.util.showAlert
import kotlinx.android.synthetic.main.activity_check_in.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val KEY_ID = "eventId"

class CheckInActivity : AppCompatActivity() {

    private val checkInViewModel: CheckInViewModel by viewModel()
    private lateinit var binding: ActivityCheckInBinding
    private var eventId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_check_in)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupObserves()
        setCheckIn()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        eventId = intent.getSerializableExtra(KEY_ID) as Int?
        intent.putExtra(KEY_ID, eventId)
        finish()
    }

    private fun setupObserves() {

        checkInViewModel.apply {
            checkIn.observe(this@CheckInActivity) {
                if (it != null) {
                    showAlert(
                        getString(R.string.title_success),
                        getString(R.string.message)
                    ) {
                        setPositiveButton(getString(R.string.button_ok)) { _, _ ->
                            finish()
                        }
                    }
                }

                isError.observe(this@CheckInActivity) { error ->
                    if (error != null) {
                        showAlert(
                            getString(R.string.title_error),
                            getString(R.string.error)
                        ) {
                            setNeutralButton(getString(R.string.button_ok), null)
                            finish()
                        }
                    }
                }

                isLoading.observe(this@CheckInActivity) {

                }
            }
        }
    }

    private fun setCheckIn() {
        eventId = intent.getSerializableExtra(KEY_ID) as Int?
        val userCheck = User()

        binding.btnConfirm.setOnClickListener {
            userCheck.id = eventId
            userCheck.name = binding.etName.text.toString()
            userCheck.email = binding.etName.text.toString()
            checkInViewModel.setCheckIn(userCheck)
        }
    }
}