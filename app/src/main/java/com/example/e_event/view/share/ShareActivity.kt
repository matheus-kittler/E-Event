package com.example.e_event.view.share

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.e_event.R
import com.example.e_event.model.Event
import com.example.e_event.model.People
import kotlinx.android.synthetic.main.activity_share.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ShareActivity : AppCompatActivity() {

    private val viewModelShare: ShareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        var detail: Event = intent.getSerializableExtra("detail") as Event

        val checkEventObserver = Observer<People> { p ->
            clLoaderCheckIn.visibility = View.GONE
            finish()
        }

        viewModelShare.currentIdEvent.observe(this, checkEventObserver)

        btnConfirm.setOnClickListener {
            clLoaderCheckIn.visibility = View.VISIBLE
            viewModelShare.checkIn(detail.id!!, etName.text.toString(), etEmail.text.toString())
        }
    }
}