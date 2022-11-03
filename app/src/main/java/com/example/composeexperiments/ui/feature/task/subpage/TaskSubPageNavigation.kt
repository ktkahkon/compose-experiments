package com.example.composeexperiments.ui.feature.task.subpage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.composeexperiments.navigation.NavigationTarget

fun NavGraphBuilder.taskSubPageRoute(navController: NavHostController) {
    composable(
        route = "task/{taskId}/subpage"
    ) { backStackEntry ->
        val taskId = backStackEntry.arguments?.getString("taskId")!!
       TaskSubPageRoute(taskId, navigateBack = navController::navigateUp)
    }
}

data class TaskSubPageNavigationRoute(val id: String): NavigationTarget {
    override fun createRouteName(): String {
        return "task/$id/subpage"
    }
}