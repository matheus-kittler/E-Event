package com.example.e_event.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.databindingtest.util.ApiResponse
import com.example.e_event.di.appModule
import com.example.e_event.model.Event
import com.example.e_event.network.service.IEventAPI
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Deferred
import mezzari.torres.lucas.network.source.Network
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import retrofit2.Response

class MainActivityViewModelTest: KoinTest  {

    @Rule
    @JvmField
    val threadRule = InstantTaskExecutorRule()

    lateinit var sub: MainActivityViewModel
    lateinit var service: IEventAPI
    lateinit var server: MockWebServer

    @Before
    fun initTest() {
        server = MockWebServer()
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    fun AuthenticationMananger(endpoint: String) {

    }

//    fun setUp() {
//        service = mock(IEventAPI::class.java) //Iniciando o serviço para maior controle
//        sub = MainActivityViewModel( // iniciando a viewModel
//            service
//        )
//    }

//    @Test
//    fun `authentication sends proper body`() {
//        server.apply {
//            enqueue(MockResponse().setBody(MockDuplexResponseBody()))
//        }
//
//        val baseUrl = server.url("http://5f5a8f24d44d640016169133.mockapi.io/api/")
//
//        startKoin(listOf(module {
//            sigle {
//                get<Network>().build<IEventAPI>(baseUrl.url().toString())
//            }
//        }))
//
//        startKoin {
//            androidLogger(Level.ERROR)
//            modules(appModule)
//        }
//
//        val testBody = LoginBody
//    }

    @Test
    fun `given a list of events when service returns successful then the list should be equal`() { //quanto mais explicativo melhor
        var hasUpdate: Boolean = false

        assertThat(//começa null
            sub.events.value,
            nullValue()
        )

        sub.events.observeForever {
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
        val call: Deferred<ApiResponse<List<Event>>> = mock()

        whenever(
            service.loadEventsAsync()
        ).thenReturn(
            call
        )

//        whenever(
//            call.enqueue(any())
//        ).then {
//            it.getArgument<Callback<List<Event>>>(0).onResponse(mock(), dummyResult)
//        } controle para que o metodo chame o onResponse


        sub.loadEvents()
        assertThat(//tem a lista
            sub.events.value,
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

        sub.events.observeForever {
            hasUpdate = true
        }

        val call: Deferred<ApiResponse<List<Event>>> = mock()

        whenever(
            service.loadEventsAsync()
        ).thenReturn(
            call
        )

//        whenever(
//            call.enqueue(any())
//        ).then {
//            it.getArgument<Callback<List<Event>>>(0).onFailure(mock(), Throwable(errorMessage))
//        } controle para que o metodo chame o onResponse


        sub.loadEvents()
        assertThat(
            sub.events.value,
            nullValue()
        )

        assertThat(
            hasUpdate,
            equalTo(false)
        )

        assertThat(
            sub.isError.value,
            equalTo(errorMessage)
        )
    }

    @Test
    fun loadListResponse() {
        var hasUpdate: Boolean = false

        sub.events.observeForever {
            hasUpdate = true
        }

        val call: Deferred<ApiResponse<List<Event>>> = mock()

        whenever(
            service.loadEventsAsync()
        ).thenReturn(
            call
        )

//        whenever(
//            call.(any())
//        ).then {
//            it.getArgument<Callback<List<Event>>>(0).onResponse(mock(), null)
//        } controle para que o metodo chame o onResponse


        sub.loadEvents()
        assertThat(
            sub.events.value,
            nullValue()
        )

        assertThat(
            hasUpdate,
            equalTo(false)
        )
    }

    @Test
    fun testModel() {
//        val event = Event( colocar os dados)
//        assertThat(1234, event.id)
    }
}