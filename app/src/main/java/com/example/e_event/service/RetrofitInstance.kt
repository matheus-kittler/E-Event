package com.example.e_event.service

import com.example.e_event.util.Contants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Contants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T>create(kClass: KClass<*>) : T {
        return retrofit.create(kClass.java) as T
    }
}