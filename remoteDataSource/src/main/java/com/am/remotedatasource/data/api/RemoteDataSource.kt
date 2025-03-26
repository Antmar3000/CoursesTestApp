package com.am.remotedatasource.data.api

class RemoteDataSource (
    private val api : ApiService
) {
        suspend fun getCourseList() = api.getCourses()
}