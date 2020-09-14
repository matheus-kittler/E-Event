package com.example.e_event.view.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_event.R

import com.example.e_event.adapter.EventAdapter
import com.example.e_event.model.Event
import com.example.e_event.view.details.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_event.*
import kotlinx.android.synthetic.main.row_event.view.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private val adapter: EventAdapter by lazy {
        EventAdapter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar

        viewModel.loadEventby()
//        viewModel.checkDetails()TODO


        val eventObserver = Observer<List<Event>> {
            adapter.events = it
                rvEventList.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    adapter = this@MainActivity.adapter

                }
        }



        val enterInDetailsEvent = Observer<Event> { id ->

//            val event: Event = id
//
//            btnMore.setOnClickListener {
//                val intent = Intent(this, DetailActivity::class.java) CRIAR HOF
//                intent.putExtra("detail", event)
//                setResult(Activity.RESULT_OK, intent)
//                finish()
//            }
        }



        viewModel.obj.observe(this, eventObserver)
        viewModel.eventId.observe(this, enterInDetailsEvent)


    }
}