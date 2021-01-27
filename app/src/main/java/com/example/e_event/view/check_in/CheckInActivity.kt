package com.example.e_event.view.check_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.e_event.R
import com.example.e_event.model.CheckIn
import com.example.e_event.model.Event
import com.example.e_event.model.People
import kotlinx.android.synthetic.main.activity_share.*

class CheckInActivity : AppCompatActivity() {

    private val viewModelCheckIn: CheckInViewModel by viewModels()
//    private lateinit var checkIn : CheckIn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail: Event = intent.getSerializableExtra("detail") as Event

//        etName.setText(checkIn.name)
//        etEmail.setText(checkIn.email)


        val checkEventObserver = Observer<CheckIn> { p ->
            p.id = detail.id
        }

        viewModelCheckIn.currentIdEvent.observe(this, checkEventObserver)

        btnConfirm.setOnClickListener {
//            viewModelCheckIn.checkIn(detail.id!!, checkIn.name!!, checkIn.email!!)
        }
    }
}