package com.am.screen_mainlist.di

import com.am.localdatasource.di.localDataModule
import com.am.remotedatasource.di.remoteDataModule
import com.am.screen_mainlist.data.repository.LocalRepositoryImpl
import com.am.screen_mainlist.data.repository.RemoteRepositoryImpl
import com.am.screen_mainlist.domain.repository.LocalRepository
import com.am.screen_mainlist.domain.repository.RemoteRepository
import com.am.screen_mainlist.domain.usecases.AddCourseToDBUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseFavListUseCase
import com.am.screen_mainlist.domain.usecases.GetLocalCourseListUseCase
import com.am.screen_mainlist.domain.usecases.GetRemoteCourseListUseCase
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val mainListModule = module {

    includes(localDataModule)
    includes(remoteDataModule)

    singleOf(::RemoteRepositoryImpl) { bind<RemoteRepository>() }
    singleOf(::LocalRepositoryImpl) { bind<LocalRepository>() }

    singleOf(::GetRemoteCourseListUseCase)
    singleOf(::GetLocalCourseFavListUseCase)
    singleOf(::AddCourseToDBUseCase)
    singleOf(::GetLocalCourseListUseCase)

    viewModelOf(::MainListViewModel)

}
