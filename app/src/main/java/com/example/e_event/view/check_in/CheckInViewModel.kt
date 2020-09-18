package com.example.e_event.view.check_in

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_event.model.People
import com.example.e_event.network.Network
import com.example.e_event.network.service.EventAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckInViewModel : ViewModel() {

    var currentIdEvent: MutableLiveData<People> = MutableLiveData()
    var save: MutableLiveData<People> = MutableLiveData()
    val service: EventAPI = Network.getInstance(EventAPI::class.java).build("http://5f5a8f24d44d640016169133.mockapi.io/api/")

    fun checkIn(id: Int, name: String, email: String) {
        service.checkInEvent(id, name, email).enqueue(object : Callback<People> {
            override fun onResponse(call: Call<People>?, response: Response<People>?) {
                if (response != null) {
                    currentIdEvent.value = response.body()
                }
            }

            override fun onFailure(call: Call<People>?, t: Throwable?) {
                Log.w("Error", "CAIU AQUI")
            }

        })
    }
}