package com.example.e_event.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    private val mainViewModel : MainActivityViewModel by viewModels()

    private val detailViewModel: DetailViewModel by viewModels()

    private val adapter: EventAdapter by lazy {
        EventAdapter(this).apply {
            onIdEventClick = { event, _ ->
                event.id?.let { detailViewModel.checkDetails(it) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel

        val events = Observer<List<Event>> {
            adapter.items = it as ArrayList<Event>
                binding.rvEventList.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    adapter = this@MainActivity.adapter
                }
        }

        mainViewModel.obj.observe(this, events)
    }
}