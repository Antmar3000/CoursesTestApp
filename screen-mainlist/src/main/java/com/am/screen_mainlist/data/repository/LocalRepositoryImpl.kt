package com.am.screen_mainlist.data.repository

import com.am.core.domain.entity.Course
import com.am.localdatasource.data.database.LocalDAO
import com.am.localdatasource.data.database.models.CourseDBO
import com.am.screen_mainlist.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(
    private val dao: LocalDAO
) : LocalRepository {

    override suspend fun updateFavourites(item: CourseDBO) {
        dao.update(item)
    }

    override suspend fun addAll(list: List<Course>) {
        list.forEach { item ->
            val itemDBO = item.toDBO()
            dao.insert(itemDBO)
        }
    }


    override fun getFavourites(): Flow<List<CourseDBO>> {
        return dao.getFavourites()
    }

    override fun getAll(): Flow<List<CourseDBO>> {
        return dao.getAll()
    }
}

fun Course.toDBO(): CourseDBO {
    return CourseDBO(
        courseId = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}