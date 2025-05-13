package com.developersbreach.kotlindictionarymultiplatform

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "list") {

            composable("list") {
                TopicListScreen { selectedTopic ->
                    navController.navigate("detail/$selectedTopic")
                }
            }

            composable(
                "detail/{topicId}",
                arguments = listOf(navArgument("topicId") { type = NavType.StringType })
            ) { navBackStackEntry ->
                val topicId = navBackStackEntry.savedStateHandle.get<String>("topicId")
                println("Navigating to DetailScreen with topicId: $topicId")
                if (topicId != null) {
                    DetailScreen(viewModel = DetailViewModel(API_KEY), topicId = topicId)
                }
            }
        }
    }
}
