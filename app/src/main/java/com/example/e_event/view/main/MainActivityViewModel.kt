package com.example.e_event.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_event.network.Network
import com.example.e_event.model.Event
import com.example.e_event.service.EventAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel() : ViewModel() {

    var obj: MutableLiveData<List<Event>> = MutableLiveData<List<Event>>()
    var eventId: MutableLiveData<Event> = MutableLiveData<Event>()
    var msg: MutableLiveData<String> = MutableLiveData<String>()
    val service: EventAPI = Network.getInstance(EventAPI::class.java).build("http://5f5a8f24d44d640016169133.mockapi.io/api/")

    fun loadEventby() {
        service.getEvent().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
                if (response != null) {
                    obj.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                Log.w("Error", "CAIU AQUI")
            }

        })
    }

    fun checkDetails(id: Int) {
        service.getDetailsEvent(id).enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>?, response: Response<Event>?) {
                if (response != null) {
                    eventId.value = response.body()
                }
            }

            override fun onFailure(call: Call<Event>?, t: Throwable?) {
                Log.w("Error", "CAIU")
            }

        })
    }

}