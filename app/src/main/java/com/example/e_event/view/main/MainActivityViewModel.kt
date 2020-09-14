package com.example.e_event.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_event.Network
import com.example.e_event.model.Event
import com.example.e_event.service.EventAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel() : ViewModel() {

//    var msg: String = "Ocorreu um erro na API"
//
//    private val api: EventAPI by lazy {
//        Network.create<EventAPI>(EventAPI::class)
//    }
//
//    //meus deus
//    fun loadEvent() {
//        api.getEvent().enqueue(object : Callback<List<Event>> {
//            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
//                if (response!!.isSuccessful) {
//                    interaction.onLoadEvent(response.body())
//                } else {
//                    interaction.onError(msg = msg)
//                }
//            }
//
//            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
//                interaction.onError(msg = msg)
//            }
//
//        })
//    }

    var obj: MutableLiveData<List<Event>> = MutableLiveData<List<Event>>()
    var msg: MutableLiveData<String> = MutableLiveData<String>()
    val service: EventAPI = Network.getInstance(EventAPI::class.java).build("http://5f5a8f24d44d640016169133.mockapi.io/api/")

    fun loadEventby(e: List<Event>) {
        service.getEvent().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
                if (response != null) {
                    obj.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }

}