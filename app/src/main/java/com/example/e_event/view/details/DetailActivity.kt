package com.example.e_event.view.details

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_event.R
import com.example.e_event.adapter.DetailAdapter
import com.example.e_event.model.Event
import com.example.e_event.model.People
import com.example.e_event.view.share.ShareActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_share.view.*

class DetailActivity : AppCompatActivity() {

    private val viewModelDetail: DetailViewModel by viewModels()
    private val adapter: DetailAdapter by lazy {
        DetailAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        var detail: Event = intent.getSerializableExtra("detail") as Event
        tvTitle.setText(detail.title)
        tvDescription.setText(detail.description)
        tvDate.setText(detail.date)
        tvPrice.setText(detail.price.toString())
        tvLatitude.setText(detail.latitude)
        tvLongitude.setText(detail.longitude)
        adapter.peoples = detail.people

        rvPeople.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.VERTICAL, false)
            adapter = this@DetailActivity.adapter

            val requestOpitons: RequestOptions by lazy {
                RequestOptions()
                    .error(R.drawable.ic_error_image)
                    .transform(CenterCrop())

            }
            Glide.with(this)
                .load(detail.image)
                .apply(requestOpitons)
                .thumbnail(0.5f)
                .into(ivPhoto)
        }

        ibShare.setOnClickListener {

            val intent = Intent(this@DetailActivity, ShareActivity::class.java)
            intent.putExtra("detail", detail)
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)

        }

//        fun showMessageBox(id: Int, name: People){
//
//            //Inflate the dialog as custom view
//            val messageBoxView = LayoutInflater.from(this).inflate(R.layout.activity_detail, null)
//
//            //AlertDialogBuilder
//            val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)
//
//            //setting text values
//            messageBoxView.etName.text = name.name
//            messageBoxView.etEmail.text = email
//
//            //show dialog
//            val  messageBoxInstance = messageBoxBuilder.show()
//
//            //set Listener
//            messageBoxView.setOnClickListener(){
//                //close dialog
//                messageBoxInstance.dismiss()
//            }
//        }
    }
}