package com.example.e_event.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_event.network.Network
import com.example.e_event.model.Event
import com.example.e_event.network.service.EventAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(
    private val service: EventAPI = Network.getInstance(EventAPI::class.java)
        .build("http://5f5a8f24d44d640016169133.mockapi.io/api/")
) : ViewModel() {

    var obj: MutableLiveData<List<Event>> = MutableLiveData<List<Event>>()
    var error: MutableLiveData<String> = MutableLiveData<String>()


    fun loadEventby() {
        service.getEvent().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
                if (response != null) {
                    obj.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                error.value = t?.message
            }
        })
    }
}