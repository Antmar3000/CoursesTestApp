package com.am.remotedatasource.data.api

import com.am.remotedatasource.data.api.models.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/data")
    suspend fun getCourses() : Response<CoursesResponse>
}