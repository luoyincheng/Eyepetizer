package com.yincheng.eyepetizer.network.base

import com.yincheng.eyepetizer.helpers.SPHelper
import com.yincheng.eyepetizer.provider.SP_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor @JvmOverloads constructor(
    private val token: String?,
    private val isScrapping: Boolean = false
) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()
        val authToken = if (token.isNullOrBlank()) SPHelper.getString(SP_TOKEN) else token
        if (!authToken.isNullOrBlank()) {
            builder.addHeader(
                "Authorization",
                if (authToken.startsWith("Basic")) authToken else "token$authToken"
            )
        }
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}