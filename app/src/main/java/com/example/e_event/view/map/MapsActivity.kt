package com.example.e_event.view.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_event.R
import com.example.e_event.model.Event

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var detail: Event = intent.getSerializableExtra("location") as Event
        // Add a marker in Sydney and move the camera
        val location = LatLng(detail.latitude!!.toDouble(), detail.longitude!!.toDouble())
        mMap.addMarker(MarkerOptions().position(location).title(detail.title))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.setMinZoomPreference(14.0f)
        mMap.setMaxZoomPreference(50.0f)
    }
}