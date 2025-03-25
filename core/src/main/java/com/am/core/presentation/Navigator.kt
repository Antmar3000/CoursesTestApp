package com.am.core.presentation

interface Navigator {
    fun navigateTo (route : String)
}

object NavRoutes {
    const val LOGIN = "login"
    const val MAIN_LIST = "mainlist"
    const val FAV_LIST = "favlist"
    const val ACCOUNT = "account"
    const val ONBOARDING = "onboarding"
}
