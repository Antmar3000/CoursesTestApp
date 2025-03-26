package com.am.screen_mainlist.domain.repository

import com.am.remotedatasource.data.api.models.CoursesResponse
import com.am.screen_mainlist.data.utils.NetworkResult

interface RemoteRepository {

    suspend fun getSafeCourseList() : NetworkResult<CoursesResponse>
}