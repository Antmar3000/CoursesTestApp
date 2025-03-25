package com.am.screen_mainlist.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.core.domain.entity.Course
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.data.utils.NetworkResult
import com.am.screen_mainlist.domain.usecases.AddCourseToDBUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseFavListUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseListUseCase
import com.am.screen_mainlist.domain.usecases.GetRemoteCourseListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainListViewModel (
    private val getRemoteUseCase : GetRemoteCourseListUseCase,
    private val getFavUseCase : GetLocalCourseFavListUseCase,
    private val addDbUseCase : AddCourseToDBUseCase,
    private val getLocalUseCase : GetLocalCourseListUseCase
) : ViewModel() {

    private val _mainList = MutableStateFlow<List<CourseDBO>>(emptyList())
    val mainList get() = _mainList.asStateFlow()

    private val _favouritesList = MutableStateFlow<List<CourseDBO>>(emptyList())
    val favouritesList get() = _favouritesList.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getRemoteUseCase.invoke().let {
                when (it) {
                    is NetworkResult.Success -> {
                        addDbUseCase.addAllItems(it.data?.courses ?: emptyList())
                        getLocalUseCase.invoke().collect {
                            _mainList.value = it
                        }
                        Log.d("myLog", "${it.data?.courses?.size}")
                    }

                    is NetworkResult.Error -> {
                        Log.d("myLog", "error")
                    }

                    is NetworkResult.Loading -> {
                        Log.d("myLog", "loading")
                    }
                }
            }
        }
    }

    fun getAllList () {
        viewModelScope.launch {
            getLocalUseCase.invoke()
                .collect { _mainList.value = it
                    Log.d("myLog", mainList.value.size.toString())}

        }
    }

    fun getFavList () {
        viewModelScope.launch {
            getFavUseCase.invoke()
                .collect { _favouritesList.value = it
                Log.d("myLog", favouritesList.value.size.toString())}
        }
    }

    fun addToFavourites (item : CourseDBO) {
        val favItem = item.copy(hasLike = !item.hasLike)
        viewModelScope.launch {
            addDbUseCase.addToFavourite(favItem)
            Log.d("myLog", "$favItem")
        }
    }

    fun removeFromFavourites (item: CourseDBO) {
        val notFavItem = item.copy(hasLike = false)
        viewModelScope.launch {
            addDbUseCase.addToFavourite(notFavItem)
        }
    }

    fun filter() {

    }
}