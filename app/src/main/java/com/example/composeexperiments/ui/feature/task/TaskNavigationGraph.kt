package com.example.composeexperiments.ui.feature.task

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.example.composeexperiments.ui.feature.task.subpage.taskSubPageRoute
import com.example.composeexperiments.ui.feature.task.taskpage.taskRoute

fun NavGraphBuilder.taskNavigationGraph(navController: NavHostController) {
    navigation(startDestination = "task/{taskId}", route = "task") {
        taskRoute(navController)
        taskSubPageRoute(navController)
    }
}