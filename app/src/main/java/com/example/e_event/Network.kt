package com.example.e_event

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Network<T> {

    private val instance: Network<T> by lazy {
        Network<T>()
    }

    private lateinit var tClass: Class<T>

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    companion object {
        fun <T> getInstance(mclass: Class<T>): Network<T> {
            return Network<T>().getInstance(mclass)
        }
    }

    private fun getInstance(mclass: Class<T>) : Network<T> {
        instance.tClass = mclass
        return instance
    }

    internal fun build(baseUrl: String = "") : T {
        return retrofitBuilder(baseUrl).create(tClass)
    }

    private fun retrofitBuilder(baseUrl: String) : Retrofit {
        val gson: Gson = GsonBuilder()
            .disableHtmlEscaping()
            .setLenient().create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(httpClient())
            .build()
    }

    private fun httpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}