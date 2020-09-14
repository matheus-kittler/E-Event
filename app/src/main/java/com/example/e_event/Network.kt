package com.example.e_event

import com.example.e_event.util.Contants
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

object Network {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Contants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    )
                    .build()
            )
            .build()
    }

    fun <T>create(kClass: KClass<*>) : T {
        return retrofit.create(kClass.java) as T
    }
}