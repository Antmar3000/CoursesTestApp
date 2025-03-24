package com.am.screen_mainlist.domain.usecases

import com.am.screen_mainlist.domain.repository.LocalRepository

class GetLocalCourseFavListUseCase (
    private val repository: LocalRepository
) {
    fun invoke() = repository.getFavourites()
}