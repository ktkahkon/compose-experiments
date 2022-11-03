package com.example.composeexperiments.ui.feature.test2

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.composeexperiments.navigation.NavigationTarget
import com.example.composeexperiments.ui.feature.test1.test1RouteName

const val test2RouteName = "test2"
fun NavGraphBuilder.test2Route(onBackClick: () -> Unit) {
    composable(
        route = test2RouteName
    ) {
        Test2Route()
    }
}


object Test2NavigationRoute : NavigationTarget {
    override fun createRouteName(): String {
        return test2RouteName
    }
}