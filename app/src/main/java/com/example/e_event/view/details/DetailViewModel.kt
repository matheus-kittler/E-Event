package com.example.e_event.view.details


import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import androidx.lifecycle.*
import com.example.databindingtest.util.Resource
import com.example.databindingtest.util.Status
import com.example.e_event.dispatcher.IAppDispatchers
import com.example.e_event.model.Event
import com.example.e_event.network.service.backend.IEventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class DetailViewModel(
    private val service: IEventService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    private val eventResource: MutableLiveData<Resource<Event>> = MutableLiveData<Resource<Event>>()

    val event: LiveData<Event> = Transformations.map(eventResource) {
        return@map it.data
    }

    val isError: LiveData<String> = Transformations.map(eventResource) {
        if (it.message != null && it.status == Status.ERROR) {
            return@map it?.message
        } else {
            return@map null
        }
    }

    val isLoading: LiveData<Boolean> = Transformations.map(eventResource) {
        return@map it.status == Status.LOADING
    }

    fun getDetails(id: Int) {
        viewModelScope.launch(dispatchers.io) {
            service.getEvent(id).collect {
                eventResource.postValue(it)
            }
        }
    }

    fun getLocation(lat: Double, lng: Double, context: Context): String? {
        val mGeocoder = Geocoder(context)
        val address = mGeocoder.getFromLocation(lat, lng, 1)
        return address[0].getAddressLine(0)
    }
}