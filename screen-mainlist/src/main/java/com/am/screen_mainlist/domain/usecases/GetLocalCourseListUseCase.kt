package com.am.screen_mainlist.domain.usecases

import com.am.screen_mainlist.domain.repository.LocalRepository

class GetLocalCourseListUseCase (
    private val repository: LocalRepository
) {
    fun invoke () = repository.getAll()
}