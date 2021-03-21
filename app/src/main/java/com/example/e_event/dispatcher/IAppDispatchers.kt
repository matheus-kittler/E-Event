package com.example.databindingtest.dispatcher

import kotlin.coroutines.CoroutineContext

interface IAppDispatchers {
    val io: CoroutineContext
    val main: CoroutineContext
}