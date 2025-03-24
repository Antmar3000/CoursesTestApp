package com.am.screen_mainlist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.am.screen_mainlist.R
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import kotlinx.coroutines.delay

@Composable
fun MainListScreen (viewModel: MainListViewModel) {

    val list = viewModel.mainList.collectAsStateWithLifecycle().value

    var searchQuery by remember { mutableStateOf("") }

//    LaunchedEffect(searchQuery) {
//        delay(200)
//        if (searchQuery.isNotEmpty()) {
//            viewModel.filter()
//        } else viewModel.getCourseList()
//    }

        Column (modifier = Modifier.fillMaxSize()) {

            Row (modifier = Modifier.fillMaxWidth()) {

                TextField(
                    value = searchQuery,
                    onValueChange = {newQuery ->
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

            Button(onClick = {viewModel.getFavList()}) {
                Text("fav")
            }
            Button(onClick = {viewModel.getAllList()}) {
                Text("all")
            }
            Button(onClick = {viewModel.addToFavourites(list[1])}) {
                Text("addFav")
            }

            LazyColumn {

            }
        }
}