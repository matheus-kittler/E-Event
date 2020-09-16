package com.example.e_event.view.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_event.model.Event
import com.example.e_event.model.People
import com.example.e_event.network.Network
import com.example.e_event.network.service.EventAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    var eventId: MutableLiveData<Event> = MutableLiveData<Event>()
    val service: EventAPI = Network.getInstance(EventAPI::class.java).build("http://5f5a8f24d44d640016169133.mockapi.io/api/")

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