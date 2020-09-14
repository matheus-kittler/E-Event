package com.example.e_event.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_event.R
import com.example.e_event.adapter.EventAdapter
import com.example.e_event.model.Event
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainInteraction {

    lateinit var event: Event
    lateinit var viewModel : MainActivityViewModel

    private val adapter by lazy {
    EventAdapter(this).apply {

    }
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainActivityViewModel(this)

        loadScreen()

        rvEventList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = this@MainActivity.adapter
        }
    }

    fun loadScreen() {
        viewModel.getEvent()
    }

    override fun onLoadEvent(event: Event) {
        return viewModel.getEvent()
    }

    override fun onError(msg: String) {

    }
}