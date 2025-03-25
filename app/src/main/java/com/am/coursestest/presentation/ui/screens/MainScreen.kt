package com.am.coursestest.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.am.core.presentation.NavRoutes
import com.am.coursestest.data.IsFirstLaunched
import com.am.screen_login.presentation.LoginScreen
import com.am.screen_mainlist.presentation.FavListScreen
import com.am.screen_mainlist.presentation.MainListScreen
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import com.am.screen_onboarding.presentation.OnboardingScreen
import org.koin.androidx.compose.KoinAndroidContext

@Composable
fun MainScreen(viewModel: MainListViewModel,
               firstLaunched: IsFirstLaunched) {

    val navController = rememberNavController()

    val startDestination = remember {
        if (firstLaunched.isFirstLaunched()) {
            firstLaunched.setLaunched()
            NavRoutes.ONBOARDING
        } else {
            NavRoutes.LOGIN
        }
    }

    KoinAndroidContext {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.ONBOARDING
        ) {

            composable(NavRoutes.ONBOARDING) {
                OnboardingScreen(navController)
            }

            composable(NavRoutes.LOGIN) {
                LoginScreen(navController)
            }

            composable(NavRoutes.MAIN_LIST) {
                MainListScreen(viewModel, navController)
            }

            composable(NavRoutes.FAV_LIST) {
                FavListScreen(viewModel, navController)
            }

            composable(NavRoutes.ACCOUNT) {
                AccountMockScreen(navController)
            }

        }
    }
}