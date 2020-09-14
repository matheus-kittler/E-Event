package com.example.e_event.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_event.Network
import com.example.e_event.model.Event
import com.example.e_event.service.EventAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(val interaction: MainInteraction) : ViewModel() {

    var msg: String = "Ocorreu um erro na API"

    private val api: EventAPI by lazy {
        Network.create<EventAPI>(EventAPI::class)
    }

    fun getEvent() {
        api.getEvent().enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>?, response: Response<Event>?) {
                if (response!!.isSuccessful) {
                    interaction.onLoadEvent(response.body())
                } else {
                    interaction.onError(msg = msg)
                }
            }

            override fun onFailure(call: Call<Event>?, t: Throwable?) {
                interaction.onError(msg = msg)
            }

        })
    }

}