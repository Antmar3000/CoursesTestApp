package com.am.screen_mainlist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.am.localdatasource.data.database.models.CourseDBO
import com.am.screen_mainlist.presentation.utils.ListItem
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel

@Composable
fun FavListContent(viewModel: MainListViewModel) {

    val list = viewModel.favouritesList.collectAsStateWithLifecycle().value

    fun onClickFav(item: CourseDBO) {
        viewModel.updateFavourite(item)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

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