package com.example.e_event.view.check_in

import androidx.lifecycle.*
import com.example.databindingtest.util.Resource
import com.example.databindingtest.util.Status
import com.example.e_event.dispatcher.IAppDispatchers
import com.example.e_event.model.CheckIn
import com.example.e_event.network.service.backend.IEventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val service: IEventService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    private val checkInResource: MutableLiveData<Resource<String>> = MutableLiveData<Resource<String>>()

    val checkIn: LiveData<String> = Transformations.map(checkInResource) {
        return@map it?.data
    }
    val isError: LiveData<String> = Transformations.map(checkInResource) {
        if (it.message != null && it.status == Status.ERROR) {
            return@map it?.message
        } else {
            return@map null
        }
    }

    val isLoading: LiveData<Boolean> = Transformations.map(checkInResource) {
        return@map it.status == Status.LOADING
    }

    fun setCheckIn(eventId: Int, name: String, email: String) {
        viewModelScope.launch(dispatchers.io) {
            service.setCheckIn(eventId, name, email).collect {
                checkInResource.postValue(it)
            }
        }
    }

}