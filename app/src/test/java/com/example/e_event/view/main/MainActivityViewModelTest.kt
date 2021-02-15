package com.example.e_event.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.e_event.model.Event
import com.example.e_event.network.service.EventAPI
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.mock
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModelTest {

    @Rule
    @JvmField
    val threadRule = InstantTaskExecutorRule()

    lateinit var sub: MainActivityViewModel
    lateinit var service: EventAPI

    @Before
    fun setUp() {
        service = mock(EventAPI::class.java) //Iniciando o serviço para maior controle
        sub = MainActivityViewModel( // iniciando a viewModel
            service
        )
    }

    @Test
    fun `given a list of events when service returns successful then the list should be equal`() { //quanto mais explicativo melhor
        var hasUpdate: Boolean = false

        assertThat(//começa null
            sub.obj.value,
            nullValue()
        )

        sub.obj.observeForever {
            hasUpdate = true
        }

        val list: List<Event> = arrayListOf(
            mock(),
            mock(),
            mock(),
            mock(),
            mock(),
            mock(),
            mock()
        )

        val dummyResult: Response<List<Event>> = Response.success(list)
        val call: Call<List<Event>> = mock()

        whenever(
            service.getEvent()
        ).thenReturn(
            call
        )

        whenever(
            call.enqueue(any())
        ).then {
            it.getArgument<Callback<List<Event>>>(0).onResponse(mock(), dummyResult)
        }// controle para que o metodo chame o onResponse


        sub.loadEventby()
        assertThat(//tem a lista
            sub.obj.value,
            equalTo(list)
        )

        assertThat(
            hasUpdate,
            equalTo(true)
        )
    }

    @Test
    fun loadErrorList() {
        var hasUpdate: Boolean = false
        val errorMessage: String = "Deu erro!"

        sub.obj.observeForever {
            hasUpdate = true
        }

        val call: Call<List<Event>> = mock()

        whenever(
            service.getEvent()
        ).thenReturn(
            call
        )

        whenever(
            call.enqueue(any())
        ).then {
            it.getArgument<Callback<List<Event>>>(0).onFailure(mock(), Throwable(errorMessage))
        }// controle para que o metodo chame o onResponse


        sub.loadEventby()
        assertThat(
            sub.obj.value,
            nullValue()
        )

        assertThat(
            hasUpdate,
            equalTo(false)
        )

        assertThat(
            sub.error.value,
            equalTo(errorMessage)
        )
    }

    @Test
    fun loadListResponse() {
        var hasUpdate: Boolean = false

        sub.obj.observeForever {
            hasUpdate = true
        }

        val call: Call<List<Event>> = mock()

        whenever(
            service.getEvent()
        ).thenReturn(
            call
        )

        whenever(
            call.enqueue(any())
        ).then {
            it.getArgument<Callback<List<Event>>>(0).onResponse(mock(), null)
        }// controle para que o metodo chame o onResponse


        sub.loadEventby()
        assertThat(
            sub.obj.value,
            nullValue()
        )

        assertThat(
            hasUpdate,
            equalTo(false)
        )
    }
}