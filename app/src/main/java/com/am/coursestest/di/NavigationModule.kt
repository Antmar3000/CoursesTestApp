package com.am.coursestest.di

import com.am.core.presentation.AccountRoute
import com.am.core.presentation.FavouriteRoute
import com.am.core.presentation.LoginRoute
import com.am.core.presentation.MainListRoute
import com.am.coursestest.presentation.navigation.Router
import org.koin.dsl.module

val navigationModule = module {

    single<AccountRoute> { Router() }
    single<FavouriteRoute> { Router() }
    single<LoginRoute> { Router() }
    single<MainListRoute> { Router() }
}