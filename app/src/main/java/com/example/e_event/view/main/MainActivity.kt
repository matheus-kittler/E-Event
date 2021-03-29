package com.example.e_event.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_event.R
import com.example.e_event.adapter.EventAdapter
import com.example.e_event.databinding.ActivityMainBinding
import com.example.e_event.model.Event
import com.example.e_event.util.showAlert
import com.example.e_event.view.details.DetailActivity
import com.example.e_event.view.details.DetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainActivityViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter: EventAdapter by lazy {
        EventAdapter(this).apply {
            onIdEventClick = { event, _ ->
                event.id?.let { eventId ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("eventId", eventId)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        setupBinding()
        setupObserves()
        setupRecyclerView()
    }

    private fun setupBinding() {
        val activity = this
        binding.apply {
            lifecycleOwner = activity
            viewModel = activity.mainViewModel
        }
    }

    private fun setupRecyclerView() {

        binding.rvEventList.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }

    private fun setupObserves() {

        mainViewModel.apply {

            loadEvents()

            events.observe(this@MainActivity) {
                adapter.events = it ?: arrayListOf()
            }

            isError.observe(this@MainActivity) {
                if (it != null) {
                    showAlert(
                        getString(R.string.title_error),
                        getString(R.string.error)
                    ) {
                        setNeutralButton("OK", null)
                    }
                }
            }

            isLoading.observe(this@MainActivity) {

            }
        }
    }
}