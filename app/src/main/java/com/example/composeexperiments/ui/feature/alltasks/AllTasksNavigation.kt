package com.example.composeexperiments.ui.feature.alltasks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.composeexperiments.navigation.NavigationTarget

const val allTasksRouteName = "allTasks"

fun NavGraphBuilder.allTasksRoute(
    navigateTo: (NavigationTarget) -> Unit
) {
    composable(
        route = allTasksRouteName
    ) {
        AllTasksRoute(
            navigateTo = navigateTo
        )
    }
}

object AllTasksRoute : NavigationTarget {
    override fun createRouteName(): String {
        return allTasksRouteName
    }
}