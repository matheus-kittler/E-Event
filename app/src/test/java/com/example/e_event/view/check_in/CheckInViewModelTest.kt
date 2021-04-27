package com.example.e_event.view.check_in

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.e_event.network.service.EventAPI
import org.mockito.Mockito.mock
import org.junit.Before
import org.junit.Rule

class CheckInViewModelTest {

    @Rule
    @JvmField
    val theadRule = InstantTaskExecutorRule()

    lateinit var sub: CheckInViewModel
    lateinit var service: EventAPI

    @Before
    fun setUp() {
        service = mock(EventAPI::class.java)
        sub = CheckInViewModel(
//            service
        )
    }
}