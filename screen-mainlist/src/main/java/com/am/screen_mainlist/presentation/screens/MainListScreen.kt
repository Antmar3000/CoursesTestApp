package com.am.screen_mainlist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.am.core.presentation.NavRoutes
import com.am.screen_mainlist.data.utils.NetworkResult
import com.am.screen_mainlist.presentation.navigation.BottomNavigation
import com.am.screen_mainlist.presentation.screens.states.ErrorScreen
import com.am.screen_mainlist.presentation.screens.states.LoadingScreen
import com.am.screen_mainlist.presentation.screens.states.SuccessScreen
import com.am.screen_mainlist.presentation.utils.ScreenStates
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel

@Composable
fun MainListScreen(viewModel: MainListViewModel) {

    val navControllerLists = rememberNavController()


    Scaffold(modifier = Modifier.navigationBarsPadding(),
        bottomBar = { BottomNavigation(navControllerLists) }) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            NavHost(
                navController = navControllerLists,
                startDestination = NavRoutes.MAIN_LIST_CONTENT
            ) {

                composable(NavRoutes.MAIN_LIST_CONTENT) {
                    MainListContent(viewModel)
                }

                composable(NavRoutes.FAV_LIST) {
                    FavListContent(viewModel)
                }

                composable(NavRoutes.ACCOUNT) {
                    AccountMockScreen()
                }
            }
        }
    }
}

@Composable
fun MainListContent(viewModel: MainListViewModel) {

    val screenState = viewModel.screenState.collectAsStateWithLifecycle().value

    when (screenState) {
        is ScreenStates.Loading -> {
            LoadingScreen()
        }

        is ScreenStates.Error -> {
            ErrorScreen(screenState.message)
        }

        is ScreenStates.Success -> {
            SuccessScreen(viewModel)
        }
    }
}





