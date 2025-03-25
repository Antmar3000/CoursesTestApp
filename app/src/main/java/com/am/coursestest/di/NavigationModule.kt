package com.am.coursestest.di

import androidx.navigation.NavHostController
import com.am.core.presentation.Navigator
import com.am.coursestest.presentation.navigation.MainNavigator
import org.koin.dsl.module

val navigationModule = module {

    single<Navigator> { (navController : NavHostController) ->
        MainNavigator(navController)
    }
}