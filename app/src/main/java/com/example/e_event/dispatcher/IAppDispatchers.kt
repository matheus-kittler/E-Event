package com.example.e_event.dispatcher

import kotlin.coroutines.CoroutineContext

interface IAppDispatchers {
    val io: CoroutineContext
    val main: CoroutineContext
}