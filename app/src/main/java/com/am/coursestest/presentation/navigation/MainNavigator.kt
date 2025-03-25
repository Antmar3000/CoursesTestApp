package com.am.coursestest.presentation.navigation

import androidx.navigation.NavHostController
import com.am.core.presentation.Navigator

class MainNavigator (private val navController: NavHostController) : Navigator {

    override fun navigateTo(route: String) {
        navController.navigate(route)
    }

}

