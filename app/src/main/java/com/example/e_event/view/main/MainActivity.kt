package com.example.e_event.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.adapter.EventAdapter
import com.example.e_event.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainActivityViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter: EventAdapter by lazy {
        EventAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        setupBinding()
        setupObserves()
        setupRecyclerView()
    }

    private fun setupBinding () {
        val activity = this
        binding.apply {
            lifecycleOwner = activity
            viewModel = activity.mainViewModel
        }
    }

    private fun setupRecyclerView () {
        val activity = this

        binding.rvEventList.apply {
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupObserves () {
        val activity = this

        mainViewModel.apply {

            events.observe(this@MainActivity) {
                adapter.events = it ?: arrayListOf()
            }

            mainViewModel.loadEvents()

//            error.observe(activity) {
//                s(
//                    getString(R.string.dialog_default_error_title),
//                    if(it == null) getString(R.string.unknown_error)
//                    else getString(it)
//                ) {
//                    setNeutralButton("OK", null)
//                }
//            }
        }
    }
}