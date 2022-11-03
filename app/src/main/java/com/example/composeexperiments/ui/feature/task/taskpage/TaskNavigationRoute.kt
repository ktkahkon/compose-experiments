package com.example.composeexperiments.ui.feature.task.taskpage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.composeexperiments.navigation.NavigationTarget
import com.example.composeexperiments.navigation.navigateTo
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.taskRoute(navController: NavHostController) {
    composable(
        route = "task/{taskId}"
    ) {
        val taskViewModel: TaskViewModel = getViewModel()
        TaskRoute(
            viewModel = taskViewModel,
            onBackClick = navController::navigateUp,
            navigateTo = navController::navigateTo
        )
    }
}

data class TaskNavigationRoute(val id: String) : NavigationTarget {
    override fun createRouteName(): String {
        return "task/$id"
    }
}
