package com.am.coursestest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.am.coursestest.presentation.ui.theme.CoursesTestAppTheme
import com.am.screen_mainlist.presentation.MainListScreen
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTestAppTheme {

                val viewModel = koinViewModel<MainListViewModel>()

                Scaffold {paddingValues ->

                    Box(modifier = Modifier.padding(paddingValues)) {
                        MainListScreen(viewModel)
                    }
                }
            }
        }
    }
}
