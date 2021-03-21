package com.example.e_event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.e_event.R
import com.example.e_event.databinding.RowEventBinding
import com.example.e_event.model.Event

class EventAdapter(
    context: Context,
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var events: List<Event> = ArrayList()
    set(value) {
        field = ArrayList(value)
        notifyDataSetChanged()
    }
    var onIdEventClick: ((Event, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            DataBindingUtil.inflate(layoutInflater, R.layout.row_event, parent, false)
        )
    }

    override fun getItemCount(): Int = events.size

    private fun getItem(position: Int): Event? {
        return events[position]
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class EventViewHolder(private val dataBinding: RowEventBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(event: Event) {
            dataBinding.apply {
                this.event = event
            }
        }
    }

}