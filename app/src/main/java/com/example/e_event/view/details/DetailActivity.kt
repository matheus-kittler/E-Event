package com.example.e_event.view.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_event.R
import com.example.e_event.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {


    private val detailViewModel : DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupBinding()

    }

    private fun setupBinding() {
        val activity = this
        binding.apply {
            lifecycleOwner = activity
            detailViewModel = activity.detailViewModel
        }
    }
}