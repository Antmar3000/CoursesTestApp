package com.am.screen_mainlist.presentation.screens.states

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.am.screen_mainlist.R
import com.am.screen_mainlist.data.database.models.CourseDBO
import com.am.screen_mainlist.presentation.utils.ListItem
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(viewModel: MainListViewModel) {

    val list = viewModel.mainList.collectAsStateWithLifecycle().value

    var searchQuery by remember { mutableStateOf("") }

    var sorted by remember { mutableStateOf(false) }

    fun onClickFav(item: CourseDBO) {
        viewModel.updateFavourite(item)
    }

    var delayedQuery by remember { mutableStateOf("") }

    val filteredList = if (!sorted) {
        list.filter { item ->
            item.title.contains(delayedQuery, ignoreCase = true) ||
                    item.text.contains(delayedQuery, ignoreCase = true)
        }.sortedBy { it.id }
    } else {
        list.filter { item ->
            item.title.contains(delayedQuery, ignoreCase = true) ||
                    item.text.contains(delayedQuery, ignoreCase = true)
        }.sortedByDescending { it.publishDate }
    }

    LaunchedEffect(searchQuery) {
        delay(200)
        delayedQuery = searchQuery
        Log.d("myLog", delayedQuery + "$sorted")
    }


    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BasicTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .height(50.dp)
                    .weight(6f)
                    .align(Alignment.CenterVertically)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = CircleShape
                    ),
                textStyle = LocalTextStyle.current.copy(
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                singleLine = true,
                decorationBox = { innerTextField ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Icon(
                            painterResource(R.drawable.search), "search",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(32.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            contentAlignment = Alignment.CenterStart,
                        ) {

                            if (searchQuery.isEmpty()) {
                                Text(
                                    "Поиск...",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Start
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(R.drawable.sort_icon),
                    tint = if (delayedQuery.isNotEmpty()) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary,
                    contentDescription = "sort"
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable {
                    sorted = !sorted
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                "По дате добавления",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Icon(
                painterResource(R.drawable.sort_arrows), "sort_arrows",
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 6.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Ничего не найдено")
            }
        } else {
            LazyColumn {
                itemsIndexed(filteredList) { index, item ->
                    ListItem(item, { onClickFav(item) })
                }
            }
        }
    }
}