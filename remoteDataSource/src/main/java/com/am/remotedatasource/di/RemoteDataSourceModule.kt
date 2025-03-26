package com.am.remotedatasource.di

import android.content.Context
import com.am.remotedatasource.data.api.ApiService
import com.am.remotedatasource.data.api.LocalJsonInterceptor
import com.am.remotedatasource.data.api.RemoteDataSource
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataModule = module {

    single { provideLocalJsonInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { get<Retrofit>().create(ApiService::class.java) }

    singleOf(::RemoteDataSource)
}


private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    return Retrofit.Builder()
        .baseUrl("http://dummyjson.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private fun provideLocalJsonInterceptor(context: Context): Interceptor {
    return LocalJsonInterceptor(context)
}

private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}