package com.example.e_event.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityMainBinding
import com.example.e_event.model.Event

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val event = Event(Array<String>(), )

    }
}