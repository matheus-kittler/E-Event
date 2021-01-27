package com.example.e_event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_event.R
import com.example.e_event.model.People
import kotlinx.android.synthetic.main.row_event.view.*
import kotlinx.android.synthetic.main.row_people_check.view.*
import kotlinx.android.synthetic.main.row_people_check.view.ivPhoto
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailAdapter(context: Context) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var items: ArrayList<People> = ArrayList()

    var peoples: List<People> set(value) {
        items = ArrayList(value)
        notifyDataSetChanged()
    } get() = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(inflater.inflate(R.layout.row_people_check, parent, false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val people = peoples[position]

        holder.itemView.apply {
            tvName.text = people.name
        }

        val uri: String = people.picture.toString()
        Glide.with(holder.itemView.context).load(uri).into(holder.itemView.ivPhoto)
    }

    override fun getItemCount(): Int = items.size

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}