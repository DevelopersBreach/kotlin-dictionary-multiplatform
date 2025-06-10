package com.developersbreach.kotlindictionarymultiplatform.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailScreen
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailViewModel
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicScreen
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(
    startDestination: AppDestinations = AppDestinations.TopicList,
) {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationAction(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<AppDestinations.TopicList> {
            val viewModel: TopicViewModel = koinViewModel()
            TopicScreen(
                onTopicClick = { selectedTopicId ->
                    actions.navigateToDetail(selectedTopicId)
                },
                viewModel = viewModel,
            )
        }

        composable<AppDestinations.Detail> {
            val viewModel: DetailViewModel = koinViewModel()
            DetailScreen(
                viewModel = viewModel,
                navigateUp = { navController.navigateUp() },
            )
        }
    }
}