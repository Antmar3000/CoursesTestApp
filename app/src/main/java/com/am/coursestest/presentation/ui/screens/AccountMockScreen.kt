package com.am.coursestest.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.am.screen_mainlist.presentation.BottomNavigation

@Composable
fun AccountMockScreen (navController: NavController) {

    Scaffold (bottomBar = { BottomNavigation(navController) }) {paddingValues ->

        Box(modifier = Modifier.padding(paddingValues),
            contentAlignment = Alignment.Center) {

            Text("Это экран-заглушка \nдля аккаунта пользователя")
        }
    }
}