package com.example.composeexperiments.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.composeexperiments.ui.feature.alltasks.allTasksRoute
import com.example.composeexperiments.ui.feature.alltasks.allTasksRouteName
import com.example.composeexperiments.ui.feature.task.taskNavigationGraph
import com.example.composeexperiments.ui.feature.task.taskpage.taskRoute
import com.example.composeexperiments.ui.feature.test1.test1Route
import com.example.composeexperiments.ui.feature.test2.test2Route

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = allTasksRouteName
    ) {
        allTasksRoute(navigateTo = navController::navigateTo)
        test1Route(onBackClick = navController::navigateUp)
        test2Route(onBackClick = navController::navigateUp)

        taskNavigationGraph(navController)
    }

}

interface NavigationTarget {
    fun createRouteName(): String
}

fun NavHostController.navigateTo(target: NavigationTarget) {
    this.navigate(target.createRouteName())
}