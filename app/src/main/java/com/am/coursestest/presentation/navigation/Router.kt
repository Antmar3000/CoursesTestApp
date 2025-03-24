package com.am.coursestest.presentation.navigation

import com.am.core.presentation.AccountRoute
import com.am.core.presentation.FavouriteRoute
import com.am.core.presentation.LoginRoute
import com.am.core.presentation.MainListRoute

class Router : AccountRoute, FavouriteRoute, LoginRoute, MainListRoute {

    override fun navigateToAccount(): String = "account_route"

    override fun navigateToFavourites(): String = "favourites_route"

    override fun navigateToLogin(): String = "login_route"

    override fun navigateToMainList(): String = "main_list_route"
}