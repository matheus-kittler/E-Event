package com.example.e_event.view.check_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.e_event.R
import com.example.e_event.model.Event
import com.example.e_event.model.People
import kotlinx.android.synthetic.main.activity_share.*
import com.example.e_event.model.CheckIn as CheckIn

@Suppress("UNREACHABLE_CODE")
class CheckInActivity : AppCompatActivity() {

    private val viewModelCheckIn: CheckInViewModel by viewModels()
    private val checkIn = CheckIn()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail: Event = intent.getSerializableExtra("detail") as Event

        btnConfirm.setOnClickListener {
            setFields()
            checkIn.id = detail.id
            viewModelCheckIn.checkIn(checkIn)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setFields() {

        checkIn.name = etName.text.toString()
        checkIn.email = etEmail.text.toString()
    }
}