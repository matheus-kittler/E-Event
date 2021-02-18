package com.example.e_event.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_event.R
import com.example.e_event.adapter.EventAdapter
import com.example.e_event.databinding.ActivityMainBinding
import com.example.e_event.model.Event
import com.example.e_event.view.details.DetailViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    private val detailViewModel: DetailViewModel by viewModels()

    private val adapter: EventAdapter by lazy {
        EventAdapter(this).apply {
            onIdEventClick = { event, _ ->
                event.id?.let { detailViewModel.checkDetails(it) }
            }
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        binding.rvEventList.apply { adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        mainViewModel.eventList.observe(this, Observer {
            adapter.items = (it ?: arrayListOf()) as ArrayList<Event>
        })
    }
}