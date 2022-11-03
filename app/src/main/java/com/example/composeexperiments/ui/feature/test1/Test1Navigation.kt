package com.example.composeexperiments.ui.feature.test1

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.composeexperiments.navigation.NavigationTarget

const val test1RouteName = "test1"

fun NavGraphBuilder.test1Route(onBackClick: () -> Unit) {
    composable(
        route = test1RouteName
    ) {
        Test1Route()
    }
}

object Test1NavigationRoute : NavigationTarget {
    override fun createRouteName(): String {
        return test1RouteName
    }
}