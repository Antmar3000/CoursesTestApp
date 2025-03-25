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
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.am.screen_mainlist.R
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import kotlinx.coroutines.delay

@Composable
fun FavListScreen(viewModel: MainListViewModel, navController: NavController) {

    val list = viewModel.favouritesList.collectAsStateWithLifecycle().value


    fun onClickFav(item: CourseDBO) {
        viewModel.addToFavourites(item)
    }

    Scaffold(bottomBar = { BottomNavigation(navController) }) { padding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            if (list.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("В избранном пока ничего нет")
                }
            } else {
                LazyColumn {
                    itemsIndexed(list) { index, item ->
                        ListItem(item, { onClickFav(item) })
                    }
                }
            }


        }
    }

}