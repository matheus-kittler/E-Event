//package com.example.e_event.view.check_in
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.e_event.model.CheckIn
//import com.example.e_event.network.service.IEventAPI
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CheckInViewModel : ViewModel() {
//
//    var currentIdEvent: MutableLiveData<CheckIn> = MutableLiveData<CheckIn>()
//    private val service: IEventAPI = Network.getInstance(IEventAPI::class.java).build("http://5f5a8f24d44d640016169133.mockapi.io/api/")
//
//    fun checkIn(checkIn: CheckIn) {
//        service.checkInEvent(checkIn).enqueue(object : Callback<CheckIn> {
//            override fun onResponse(call: Call<CheckIn>?, response: Response<CheckIn>?) {
//                if (response != null) {
//                    currentIdEvent.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<CheckIn>?, t: Throwable?) {
//                Log.w("Error", "CAIU AQUI")
//            }
//
//        })
//    }
//}