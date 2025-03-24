package com.am.screen_mainlist.domain.usecases

import android.util.Log
import com.am.core.domain.entity.Course
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.domain.repository.LocalRepository

class AddCourseToDBUseCase (
    private val repository: LocalRepository
) {
    suspend fun addAllItems (list : List<Course>) {
        repository.addAll(list)
    }

    suspend fun addToFavourite (item : CourseDBO) {
        repository.addToFavourites(item)
    }
}