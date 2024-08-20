package com.mleiva.calendar_events.data

import com.mleiva.calendar_events.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data
 * Creted by: Marcelo Leiva on 15-08-2024 at 16:21
 ***/
object EventsClient {

    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor(::apiKeyAsQuery)
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://app.ticketmaster.com/discovery/v2/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<EventsService>()

}

private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain.request()
        .newBuilder()
        .url(
            chain
                .request()
                .url
                .newBuilder()
                .addQueryParameter("apikey", BuildConfig.TM_API_KEY)
                .build()
        )
        .build()
)

