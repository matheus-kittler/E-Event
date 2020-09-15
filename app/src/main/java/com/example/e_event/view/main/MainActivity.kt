package com.example.e_event.view.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_event.R

import com.example.e_event.adapter.EventAdapter
import com.example.e_event.model.Event
import com.example.e_event.view.details.DetailActivity
import com.example.e_event.view.details.DetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_event.*
import kotlinx.android.synthetic.main.row_event.view.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private val viewModelDetail: DetailViewModel by viewModels()
    private val adapter: EventAdapter by lazy {
        EventAdapter(this).apply {
            onIdEventClick = { event, _ ->
                clLoader.visibility = View.VISIBLE
                viewModelDetail.checkDetails(event.id!!)

                event
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clLoader.visibility = View.VISIBLE

        viewModel.loadEventby()

        val eventObserver = Observer<List<Event>> {
            clLoader.visibility = View.GONE
            adapter.events = it
                rvEventList.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    adapter = this@MainActivity.adapter

                }
        }

        val enterInDetailsEvent = Observer<Event> { id ->
            clLoader.visibility = View.GONE
            val event: Event = id
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("detail", event)
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)
        }

        viewModel.obj.observe(this, eventObserver)
        viewModelDetail.eventId.observe(this, enterInDetailsEvent)


    }
}