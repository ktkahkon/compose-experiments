package com.example.composeexperiments.ui.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.composeexperiments.navigation.NavigationTarget
import com.example.composeexperiments.ui.feature.alltasks.AllTasksRoute
import com.example.composeexperiments.ui.feature.alltasks.allTasksRouteName
import com.example.composeexperiments.ui.feature.test1.Test1NavigationRoute
import com.example.composeexperiments.ui.feature.test1.test1RouteName
import com.example.composeexperiments.ui.feature.test2.Test2NavigationRoute
import com.example.composeexperiments.ui.feature.test2.test2RouteName

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    navigateTo: (NavigationTarget) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = null
                )
            },
            label = { Text("Tasks") },
            selected = allTasksRouteName === currentRoute,
            onClick = { navigateTo(AllTasksRoute) }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Rounded.List,
                    contentDescription = null
                )
            },
            label = { Text("Test 1") },
            selected = test1RouteName === currentRoute,
            onClick = { navigateTo(Test1NavigationRoute) }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Rounded.Settings,
                    contentDescription = null
                )
            },
            label = { Text("Test 2") },
            selected = test2RouteName === currentRoute,
            onClick = { navigateTo(Test2NavigationRoute) }
        )
    }
}