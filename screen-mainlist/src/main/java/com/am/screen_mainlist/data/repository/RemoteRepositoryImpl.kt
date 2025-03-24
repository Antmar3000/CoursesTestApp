package com.am.screen_mainlist.data.repository

import com.am.core.domain.entity.Course
import com.am.screen_mainlist.data.api.RemoteDataSource
import com.am.screen_mainlist.data.api.models.CoursesResponse
import com.am.screen_mainlist.data.utils.NetworkResult
import com.am.screen_mainlist.data.utils.SafeApiResponse
import com.am.screen_mainlist.domain.repository.RemoteRepository

class RemoteRepositoryImpl (
    private val dataSource: RemoteDataSource
) : RemoteRepository, SafeApiResponse() {

    override suspend fun getSafeCourseList() : NetworkResult<CoursesResponse> {
        return safeApiCall { dataSource.getCourseList() }
    }
}