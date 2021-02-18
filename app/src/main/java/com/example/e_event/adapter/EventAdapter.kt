package com.example.e_event.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.e_event.R
import com.example.e_event.databinding.RowEventBinding
import com.example.e_event.model.Event
import kotlinx.android.synthetic.main.row_event.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventAdapter(private val context: Context) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    private var _items: ArrayList<Event> = ArrayList()
    var items: ArrayList<Event> set(value) {
        _items = ArrayList(value)
        notifyDataSetChanged()
    } get() = _items

    private val DATE_FORMAT = "dd/MM/yyyy"

//    var events: List<Event>
//        set(value) {
//            items = ArrayList(value)
//            notifyDataSetChanged()
//        }
//        get() = items

    var onIdEventClick: ((Event, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(DataBindingUtil.inflate(inflater, R.layout.row_event,parent, false))

    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = _items[position]

//        holder.itemView.apply {
//            tvDate.text = SimpleDateFormat(DATE_FORMAT, Locale.US).format(Date(event.date!!))
//            tvTitle.text = event.title
//            tvDescription.text = event.description
//            tvPrice.text = event.price.toString().replace(".", ",").replaceAfter("0", "0")
//
//            holder.itemView.btnMore.setOnClickListener {
//                onIdEventClick?.invoke(event, position)
//            }
//
//        }

        (holder).also {
            val eventId = items[position]
            it.bind(eventId)
            it.itemView.setOnClickListener { onIdEventClick?.invoke(eventId, position) }
        }

        Glide.with(context)
            .load(event.image)
            .error(R.drawable.ic_error_image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(holder.itemView.ivPhoto)

    }

    override fun getItemCount(): Int = _items.size

    class EventViewHolder(private val dataBinding: RowEventBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(event: Event) {
            dataBinding.event = event
        }
    }

}