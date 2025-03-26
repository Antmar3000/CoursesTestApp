package com.am.remotedatasource.data.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class LocalJsonInterceptor (
    private val context : Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val jsonFileName = when {
            uri.endsWith("/api/data") -> "courses.json"
            else -> null
        }

        return if (jsonFileName != null) {

            val jsonData = context.assets.open(jsonFileName).bufferedReader().use { it.readText() }

            Response.Builder()
                .code(200)
                .message(jsonData)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(jsonData.toResponseBody("application/json".toMediaTypeOrNull()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            chain.proceed(chain.request())
        }
    }


}