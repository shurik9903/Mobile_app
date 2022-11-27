package com.example.retrofitforecaster.module

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        Timber.i("Sending request ${request.url()} on ${chain.connection()} \n ${request.headers()}")

        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        Timber.i("Received response for ${response.request().url()} in ${(t2 - t1) / 1e6} \n ${response.headers()}")

        return response
    }
}