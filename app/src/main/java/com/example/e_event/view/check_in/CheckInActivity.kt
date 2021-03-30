package com.example.e_event.view.check_in

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityCheckInBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInActivity : AppCompatActivity() {

    private val checkInViewModel: CheckInViewModel by viewModel()
    private lateinit var binding: ActivityCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_check_in)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
}