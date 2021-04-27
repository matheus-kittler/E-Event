package com.example.e_event.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.e_event.model.Event
import com.example.e_event.network.service.EventAPI
import com.example.e_event.view.details.DetailViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat
import org.mockito.Mockito.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModelTest {

    @Rule
    @JvmField
    val threadRule = InstantTaskExecutorRule()

    lateinit var sub: DetailViewModel
    lateinit var service: EventAPI

    @Before
    fun setuUp() {
        service = mock(EventAPI::class.java)
        sub = DetailViewModel(
            service
        )
    }

    @Test
    fun `when detail event returns successful the data` () {
        var hasUpdate: Boolean = false

        assertThat(
            sub.eventId.value,
            nullValue()
        )

        sub.eventId.observeForever {
            hasUpdate = true
        }

        val detailEvent: Event = Event(
            null,
            840682800000,
            "Amigos do Inferno",
            null,
            -44.9357,
            -2.92887,
            19.99,
            "Evento dos Amigos",
            4
        )

        val dummyResult: Response<Event> = Response.success(detailEvent)
        val call: Call<Event> = mock()

        whenever(
            detailEvent.id?.let { service.getDetailsEvent(it) }
        ).thenReturn(
            call
        )

        whenever(
            call.enqueue(any())
        ).then {
            it.getArgument<Callback<Event>>(0).onResponse(mock(), dummyResult)
        }

        detailEvent.id?.let { sub.checkDetails(it) }
        assertThat(
            sub.eventId.value,
            equalTo(detailEvent)
        )

        assertThat(
            hasUpdate,
            equalTo(true)
        )
    }

    @Test
    fun `when detail event returns failed the data` () {
        var hasUpdate: Boolean = false
        val errorMsg: String = "Deu erro!"

        sub.eventId.observeForever {
            hasUpdate = true
        }

        val call: Call<Event> = mock()

        whenever(
            service.getDetailsEvent(0)
        ).thenReturn(
            call
        )

        whenever(
            call.enqueue(any())
        ).then {
            it.getArgument<Callback<Event>>(0).onFailure(mock(), Throwable(errorMsg))
        }

        sub.checkDetails(0)
        assertThat(
            sub.eventId.value,
            nullValue()
        )

        assertThat(
            hasUpdate,
            equalTo(false)
        )

        assertThat(
            sub.error.value,
            equalTo(errorMsg)
        )
    }
}