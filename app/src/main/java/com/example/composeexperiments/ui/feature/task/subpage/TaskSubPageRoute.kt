package com.example.composeexperiments.ui.feature.task.subpage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.composeexperiments.ui.components.ScreenWithTopBar

@Composable
fun TaskSubPageRoute(
    taskId: String,
    navigateBack: () -> Unit
) {
    val state = TaskSubPagePresenter(taskId)
    TaskSubPageScreen(
        state,
        navigateBack
    )
}

@Composable
fun TaskSubPageScreen(
    state: TaskSubPageState,
    navigateBack: () -> Unit
) {
    ScreenWithTopBar(title = "Task subpage", onNavigationAction = navigateBack) {
        Text(state.title)
    }
}