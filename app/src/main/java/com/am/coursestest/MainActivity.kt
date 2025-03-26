package com.am.coursestest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.am.coursestest.data.IsFirstLaunched
import com.am.coursestest.presentation.ui.screens.MainScreen
import com.am.coursestest.presentation.ui.theme.CoursesTestAppTheme
import com.am.screen_mainlist.presentation.viewmodels.MainListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTestAppTheme {

                val viewModel = koinViewModel<MainListViewModel>()

                val isFirstLaunched: IsFirstLaunched by inject()

                MainScreen(viewModel, isFirstLaunched)
            }
        }
    }
}
