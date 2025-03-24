package com.am.screen_mainlist.domain.usecases

import com.am.screen_mainlist.domain.repository.RemoteRepository

class GetRemoteCourseListUseCase (
    private val repository: RemoteRepository
) {

    suspend fun invoke() = repository.getSafeCourseList()
}