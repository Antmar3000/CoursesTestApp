package com.am.screen_mainlist.di

import android.content.Context
import androidx.room.Room
import com.am.screen_mainlist.data.api.ApiService
import com.am.screen_mainlist.data.api.LocalJsonInterceptor
import com.am.screen_mainlist.data.api.RemoteDataSource
import com.am.screen_mainlist.data.database.LocalDatabase
import com.am.screen_mainlist.data.repository.LocalRepositoryImpl
import com.am.screen_mainlist.data.repository.RemoteRepositoryImpl
import com.am.screen_mainlist.domain.repository.LocalRepository
import com.am.screen_mainlist.domain.repository.RemoteRepository
import com.am.screen_mainlist.domain.usecases.AddCourseToDBUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseFavListUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseListUseCase
import com.am.screen_mainlist.domain.usecases.GetRemoteCourseListUseCase
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val mainListModule = module {

    single {
        Room.databaseBuilder(androidContext(),
            LocalDatabase::class.java,
            "fav-database")
            .build()
    }
    single { get<LocalDatabase>().localDao() }


    single { provideLocalJsonInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { get<Retrofit>().create(ApiService::class.java) }

    singleOf(::RemoteDataSource)

    singleOf(::RemoteRepositoryImpl) { bind<RemoteRepository>() }
    singleOf(::LocalRepositoryImpl) { bind<LocalRepository>() }

    singleOf(::GetRemoteCourseListUseCase)
    singleOf(::GetLocalCourseFavListUseCase)
    singleOf(::AddCourseToDBUseCase)
    singleOf(::GetLocalCourseListUseCase)

    viewModelOf(::MainListViewModel)

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