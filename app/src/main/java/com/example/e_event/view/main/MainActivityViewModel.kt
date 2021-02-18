package com.example.e_event.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_event.network.Network
import com.example.e_event.model.Event
import com.example.e_event.network.service.EventAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivityViewModel(
    private val service: EventAPI = Network.getInstance(EventAPI::class.java)
        .build("http://5f5a8f24d44d640016169133.mockapi.io/api/")
) : ViewModel() {

    private val _eventList: MutableLiveData<List<Event>> = MutableLiveData()
    val eventList: MutableLiveData<List<Event>> get() = _eventList
    var error: MutableLiveData<String> = MutableLiveData<String>()


    fun getEvents() {
        service.getEvent().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
                if (response != null) {
                    eventList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                error.value = t?.message
            }
        })
    }

//    private fun getEvents() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = service.getEvent()
//                if (response.isExecuted) {
//                    eventList.postValue(response.body())
//                }
//            } catch (e: Exception) {
//
//            }
//        }
//    }
}