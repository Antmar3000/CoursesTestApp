package com.am.screen_mainlist.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.am.core.presentation.NavRoutes
import com.am.screen_mainlist.R

@Composable
fun BottomNavigation(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem(
            title = NavRoutes.MAIN_LIST_CONTENT,
            name = "Главная",
            selectedIcon = ImageVector.vectorResource(R.drawable.main_green),
            unselectedIcon = ImageVector.vectorResource(R.drawable.main)
        ),
        BottomNavItem(
            title = NavRoutes.FAV_LIST,
            name = "Избранное",
            selectedIcon = ImageVector.vectorResource(R.drawable.fav_green),
            unselectedIcon = ImageVector.vectorResource(R.drawable.favourites)
        ),
        BottomNavItem(
            title = NavRoutes.ACCOUNT,
            name = "Аккаунт",
            selectedIcon = ImageVector.vectorResource(R.drawable.account_green),
            unselectedIcon = ImageVector.vectorResource(R.drawable.account)
        )
    )

    Box(contentAlignment = Alignment.BottomCenter) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            HorizontalDivider(
                modifier = Modifier.height(2.dp),
                color = MaterialTheme.colorScheme.tertiary
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                items.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = currentRoute == item.title,
                        onClick = {
                            navController.navigate(item.title)
                        },
                        label = {
                            Text(
                                item.name,
                                color = if (currentRoute == item.title)
                                    MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.secondary
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = if (currentRoute == item.title) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title,
                                tint = if (currentRoute == item.title) {
                                    MaterialTheme.colorScheme.primary
                                } else MaterialTheme.colorScheme.secondary
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
            }
        }
    }
}

data class BottomNavItem(
    val title: String,
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)