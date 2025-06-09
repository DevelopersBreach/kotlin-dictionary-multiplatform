package com.developersbreach.kotlindictionarymultiplatform.ui.navigation

import androidx.navigation.NavHostController

class NavigationAction(
    private val navController: NavHostController,
) {

    val navigateToDetail: (String) -> Unit = { topicId ->
        navController.navigate(AppDestinations.Detail(topicId))
    }
}