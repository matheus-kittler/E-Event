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
    private lateinit var binding: ActivityMainBinding

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
            adapter = EventAdapter(
                activity,
                activity.mainViewModel.obj
            )
        }
    }

    private fun setupObserves () {
        val activity = this

        mainViewModel.apply {

            obj.observe(activity) {
                binding.rvEventList.adapter?.notifyDataSetChanged()
            }

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