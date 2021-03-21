//package com.example.e_event.view.details
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.e_event.model.Event
//import com.example.e_event.network.service.IEventAPI
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class DetailViewModel : ViewModel() {
//
//    var eventId: MutableLiveData<Event> = MutableLiveData<Event>()
//    var error: MutableLiveData<String> = MutableLiveData<String>()
//    val service: IEventAPI = Network.getInstance(IEventAPI::class.java).build("http://5f5a8f24d44d640016169133.mockapi.io/api/")
//
//    fun checkDetails(id: Int) {
//        service.getDetailsEvent(id).enqueue(object : Callback<Event> {
//            override fun onResponse(call: Call<Event>?, response: Response<Event>?) {
//                if (response != null) {
//                    eventId.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<Event>?, t: Throwable?) {
//                error.value = t?.message
//            }
//        })
//    }
//
//
//}