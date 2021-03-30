package com.example.e_event.view.check_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityCheckInBinding
import com.example.e_event.model.User
import com.example.e_event.util.showAlert
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        setUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        eventId = intent.getSerializableExtra("eventId") as Int?
        intent.putExtra("eventId", eventId)
        finish()
    }

    private fun setupObserves() {

        checkInViewModel.apply {

//            checkInViewModel.setCheckIn(1, "Matheus", "matheus@gmail.com")

            checkIn.observe(this@CheckInActivity) {
                if (it != null) {
                    showAlert(
                        "Sucesso!",
                        "Seu check-in foi feito com sucesso, muito obrigado!"
                    ) {
                        setNeutralButton("OK", null)
                    }
                }
            }

            isError.observe(this@CheckInActivity) {
                if (it != null) {
                    showAlert(
                        getString(R.string.title_error),
                        getString(R.string.error)
                    ) {
                        setNeutralButton("OK", null)
                        finish()
                    }
                }
            }

            isLoading.observe(this@CheckInActivity) {

            }
        }
    }

    private fun setUp() {
        eventId = intent.getSerializableExtra("eventId") as Int?
        val userCheck: User = User()

        binding.btnConfirm.setOnClickListener {
            userCheck.id = eventId
            userCheck.name = binding.etName.text.toString()
            userCheck.email = binding.etName.text.toString()
            checkInViewModel.setCheckIn(userCheck)
        }
    }
}