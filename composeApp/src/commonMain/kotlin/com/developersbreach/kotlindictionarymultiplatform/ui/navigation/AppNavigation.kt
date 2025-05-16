package com.developersbreach.kotlindictionarymultiplatform.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.DetailScreen
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.DetailViewModel
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.TopicListScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(
    startDestination: AppDestinations = AppDestinations.TopicList,
) {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable<AppDestinations.TopicList> {
            TopicListScreen(
                onTopicClick = { selectedTopicId ->
                    actions.navigateToDetail(selectedTopicId)
                }
            )
        }

        composable<AppDestinations.Detail> {
            val viewModel: DetailViewModel = koinViewModel()
            DetailScreen(viewModel = viewModel)
        }
    }
}
