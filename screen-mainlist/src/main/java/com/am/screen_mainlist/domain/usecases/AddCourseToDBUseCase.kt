package com.am.screen_mainlist.domain.usecases

import com.am.core.domain.entity.Course
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.domain.repository.LocalRepository

class AddCourseToDBUseCase (
    private val repository: LocalRepository
) {
    suspend fun addAllItems (list : List<Course>) {
        repository.addAll(list)
    }

    suspend fun updateFavourite (item : CourseDBO) {
        val favItem = item.copy(hasLike = !item.hasLike)
        repository.updateFavourites(favItem)
    }
}