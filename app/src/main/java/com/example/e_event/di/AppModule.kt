package com.example.e_event.di


import com.example.e_event.dispatcher.AppDispatchers
import com.example.e_event.dispatcher.IAppDispatchers
import com.example.e_event.network.module.RetrofitModule
import com.example.e_event.network.service.backend.EventService
import com.example.e_event.network.service.IEventAPI
import com.example.e_event.network.service.backend.IEventService
import com.example.e_event.view.check_in.CheckInViewModel
import com.example.e_event.view.details.DetailViewModel
import com.example.e_event.view.main.MainActivityViewModel
import mezzari.torres.lucas.network.source.Network
import mezzari.torres.lucas.network.source.module.client.LogModule
import mezzari.torres.lucas.network.source.module.retrofit.GsonConverterModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val genericModule = module {
    single<IAppDispatchers> { AppDispatchers() }
}

val networkModule = module {
    single {
        Network.initialize(
                retrofitLevelModules = arrayListOf(GsonConverterModule(), RetrofitModule()),
                okHttpClientLevelModule = arrayListOf(LogModule())
        )

        return@single Network
    }

    single { get<Network>().build<IEventAPI>("http://5f5a8f24d44d640016169133.mockapi.io/api/") }


    single<IEventService> { EventService(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { CheckInViewModel(get(), get()) }
}

val appModule = listOf(genericModule, networkModule, viewModelModule)