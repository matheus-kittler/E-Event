package com.example.e_event.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_event.R
import com.example.e_event.model.Event
import com.example.e_event.view.details.DetailActivity
import kotlinx.android.synthetic.main.row_event.view.*

class EventAdapter(private val context: Context) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var items: ArrayList<Event> = ArrayList()

    var events: List<Event> set(value) {
        items = ArrayList(value)
        notifyDataSetChanged()
    } get() = items

    var onIdEventClick: ((Event, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(inflater.inflate(R.layout.row_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        holder.itemView.apply {
            tvTitle.text = event.title
            tvDescription.text = event.description
            tvPrice.text = event.price.toString()

            val requestOpitons: RequestOptions by lazy {
                RequestOptions()
                    .error(R.drawable.ic_error_image)
                    .transform(CenterCrop())

            }
            Glide.with(context)
                .load(event.image)
                .apply(requestOpitons)
                .thumbnail(0.5f)
                .into(ivPhoto)

            holder.itemView.btnMore.setOnClickListener {
                onIdEventClick?.invoke(event, position)
            }

        }

        val uri: String = event.image.toString()
        Glide.with(holder.itemView.context).load(uri).into(holder.itemView.ivPhoto)


//        holder.itemView.setOnClickListener {
//            if (holder != null) {
//                onEventDetailClickListener.onEventDetailClick(event, position)
//            }
//        }
    }

    override fun getItemCount(): Int = events.size

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnEventDetailClickListener {
        fun onEventDetailClick(event: Event, index: Int)
    }
}