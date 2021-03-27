package com.example.e_event.view.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityDetailBinding
import com.example.e_event.model.Event
import com.example.e_event.util.showAlert
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class DetailActivity : AppCompatActivity() {


    private val detailViewModel : DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupBinding()
        setupObserves()

    }

    private fun setupBinding() {
        val activity = this
        binding.apply {
            lifecycleOwner = activity
            detailViewModel = activity.detailViewModel
        }
    }

    private fun setupObserves () {

//        val detail: Event = intent.getSerializableExtra("detail") as Event
//
//        detail.id

        detailViewModel.apply {


            detail.observe(this@DetailActivity) {
                it
            }

//            detailEvent.observe(this@DetailActivity) {
//                detail
//            }
//            events.observe(this@MainActivity) {
//                adapter.events = it ?: arrayListOf()
//            }
//
//            mainViewModel.loadEvents()
//
//            isError.observe(this@MainActivity) {
//                if (it != null) {
//                    showAlert(
//                        getString(R.string.title_error),
//                        getString(R.string.error)
//                    ) {
//                        setNeutralButton("OK", null)
//                    }
//                }
//            }
//
//            isLoading.observe(this@MainActivity) {
//
//            }
        }
    }
}