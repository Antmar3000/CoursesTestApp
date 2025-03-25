package com.am.screen_mainlist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.am.core.presentation.NavRoutes
import com.am.screen_mainlist.R
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import kotlinx.coroutines.delay

@Composable
fun MainListScreen (viewModel: MainListViewModel, navController: NavController) {

    val list = viewModel.mainList.collectAsStateWithLifecycle().value

    var searchQuery by remember { mutableStateOf("") }

    fun onClickFav (item : CourseDBO) {
        viewModel.addToFavourites(item)
    }

    var delayedQuery by remember { mutableStateOf("") }

    val filteredList = list.filter { item ->
        item.title.contains(delayedQuery, ignoreCase = true) ||
                item.text.contains(delayedQuery, ignoreCase = true)
    }

    LaunchedEffect(searchQuery) {
        delay(200)
        delayedQuery = searchQuery
    }

    Scaffold (bottomBar = { BottomNavigation(navController) }) { padding ->

        Column(modifier = Modifier.fillMaxSize().padding(padding)) {

            Row(modifier = Modifier.fillMaxWidth()) {

                TextField(
                    value = searchQuery,
                    onValueChange = { newQuery ->
                        searchQuery = newQuery
                    },
                    modifier = Modifier.weight(8f),
                    placeholder = { Text("Search...") }
                )

                Image(
                    painter = painterResource(R.drawable.sort),
                    contentDescription = "sort"
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = { viewModel.getFavList() }) {
                Text("fav")
            }
            Button(onClick = { viewModel.getAllList() }) {
                Text("all")
            }
            Button(onClick = { viewModel.addToFavourites(list[1]) }) {
                Text("addFav")
            }

            if (filteredList.isEmpty()) {
                Box (modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text("Ничего не найдено")
                }
            }
            else {
                LazyColumn {
                    itemsIndexed(filteredList) {index, item ->
                        ListItem(item, {onClickFav(item)})
                    }
                }
            }


        }
    }
}

@Composable
fun BottomNavigation (navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem(
            title = NavRoutes.MAIN_LIST,
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Default.Call
        ),
        BottomNavItem(
            title = NavRoutes.FAV_LIST,
            selectedIcon = Icons.Default.Check,
            unselectedIcon = Icons.Default.Clear
        ),
        BottomNavItem(
            title = NavRoutes.ACCOUNT,
            selectedIcon = Icons.Default.AccountBox,
            unselectedIcon = Icons.Default.AccountCircle
        )
    )

    NavigationBar(modifier = Modifier.height(80.dp)) {
        items.forEachIndexed {_, item ->
            NavigationBarItem(
                selected = currentRoute == item.title,
                onClick = {navController.navigate(item.title)},
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.title) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

data class BottomNavItem (
    val title : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector
)