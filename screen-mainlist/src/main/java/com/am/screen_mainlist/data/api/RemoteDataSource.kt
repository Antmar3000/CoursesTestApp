package com.am.screen_mainlist.data.api

class RemoteDataSource (
    private val api : ApiService
) {
        suspend fun getCourseList() = api.getCourses()
}