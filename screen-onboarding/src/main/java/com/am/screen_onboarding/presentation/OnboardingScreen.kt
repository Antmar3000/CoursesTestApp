package com.am.screen_onboarding.presentation

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.am.core.presentation.NavRoutes
import com.am.screen_onboarding.data.GridItem
import com.am.screen_onboarding.data.list1
import com.am.screen_onboarding.data.list2
import com.am.screen_onboarding.data.list3
import com.am.screen_onboarding.data.list4
import com.am.screen_onboarding.data.list5

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {

    val scrollState = rememberScrollState(300)

    val listItems = remember {
        mutableStateListOf(
            list1, list2, list3, list4, list5
        )
    }
    val listItemsNew: List<List<GridItem>> = listItems


    fun setTinted(id: Int, id2: Int, isTinted: Boolean) {
        val updatedList = listItems[id].map {
            if (it.id == id2) it.copy(isTinted = !isTinted) else it
        }
        listItems[id] = updatedList.toMutableList()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            "Тысячи курсов \nв одном месте",
            fontSize = 24.sp,
            color = Color.White,
            lineHeight = 30.sp
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {

            Column(
                modifier = Modifier
                    .horizontalScroll(scrollState)
            ) {
                listItemsNew.forEach { rowItems ->
                    FlowRow(
                        modifier = Modifier.padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        rowItems.forEach { item ->
                            AngledCard(
                                item,
                                item.isTinted,
                                {
                                    setTinted(
                                        listItemsNew.indexOf(rowItems),
                                        item.id,
                                        item.isTinted
                                    )
                                })
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { navController.navigate(NavRoutes.LOGIN) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Продолжить")
            }
        }
    }
}

//@Preview
//@Composable
//fun OnboardingPreview() {
//    OnboardingScreen()
//}