package com.example.e_event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_event.R
import com.example.e_event.databinding.RowEventBinding
import com.example.e_event.model.Event
import kotlinx.android.synthetic.main.row_event.view.*

class EventAdapter(
    context: Context
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

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

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        (holder as EventViewHolder).also {
            val event = events[position]
            it.bind(event)
            it.itemView.apply {
                this.btnMore.setOnClickListener {
                    onIdEventClick?.invoke(event, position)
                }
            }
        }
    }

    class EventViewHolder(private val dataBinding: RowEventBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(event: Event) {
            dataBinding.event = event
        }
    }

}