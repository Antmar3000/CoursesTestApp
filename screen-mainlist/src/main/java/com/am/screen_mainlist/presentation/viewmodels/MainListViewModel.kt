package com.am.screen_mainlist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.data.utils.NetworkResult
import com.am.screen_mainlist.domain.usecases.AddCourseToDBUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseFavListUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseListUseCase
import com.am.screen_mainlist.domain.usecases.GetRemoteCourseListUseCase
import com.am.screen_mainlist.presentation.utils.ScreenStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainListViewModel(
    private val getRemoteUseCase: GetRemoteCourseListUseCase,
    private val getFavUseCase: GetLocalCourseFavListUseCase,
    private val addDbUseCase: AddCourseToDBUseCase,
    private val getLocalUseCase: GetLocalCourseListUseCase
) : ViewModel() {

    private val _mainList = MutableStateFlow<List<CourseDBO>>(emptyList())
    val mainList get() = _mainList.asStateFlow()

    private val _favouritesList = MutableStateFlow<List<CourseDBO>>(emptyList())
    val favouritesList get() = _favouritesList.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenStates>(ScreenStates.Loading)
    val screenState get() = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getRemoteUseCase.invoke().let { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> {
                        _screenState.value = ScreenStates.Success
                        addDbUseCase.addAllItems(networkResult.data?.courses ?: emptyList())
                        getAllList()
                        getFavList()
                    }

                    is NetworkResult.Error -> {
                        _screenState.value = ScreenStates.Error(networkResult.message)
                    }

                    is NetworkResult.Loading -> {
                        _screenState.value = ScreenStates.Loading
                    }
                }
            }
        }
    }

    private fun getAllList() {
        viewModelScope.launch {
            getLocalUseCase.invoke()
                .collect { _mainList.value = it }
        }
    }

    private fun getFavList() {
        viewModelScope.launch {
            getFavUseCase.invoke()
                .collect { _favouritesList.value = it }
        }
    }

    fun updateFavourite(item: CourseDBO) {
        val favItem = item.copy(hasLike = !item.hasLike)
        viewModelScope.launch {
            addDbUseCase.updateFavourite(favItem)
        }
    }
}