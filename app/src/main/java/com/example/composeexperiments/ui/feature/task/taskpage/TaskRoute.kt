package com.example.composeexperiments.ui.feature.task.taskpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeexperiments.navigation.NavigationTarget
import com.example.composeexperiments.ui.components.ScreenWithTopBar
import com.example.composeexperiments.ui.feature.task.subpage.TaskSubPageNavigationRoute

@Composable
fun TaskRoute(
    viewModel: TaskViewModel,
    onBackClick: () -> Unit,
    navigateTo: (NavigationTarget) -> Unit
) {
    val state by viewModel.state.collectAsState()

    ScreenWithTopBar(
        title = "Task ${state.taskId}",
        onNavigationAction = onBackClick
    ) {
        TaskScreenContent(
            state = state,
            navigateTo = navigateTo,
            handleEvent = viewModel::handleEvent
        )
    }
}

@Composable
fun TaskScreenContent(
    state: TaskState,
    navigateTo: (NavigationTarget) -> Unit,
    handleEvent: (TaskEvent) -> Unit
) {

    var text by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        Text("Task ${state.taskId}")

        OutlinedButton(onClick = { navigateTo(TaskSubPageNavigationRoute(state.taskId)) }) {
            Text("Navigate")
        }

        OutlinedButton(onClick = { handleEvent(TaskEvent.PerformAction) }) {
            Text("Perform action")
        }

        OutlinedButton(onClick = { handleEvent(TaskEvent.PerformAnotherAction(text)) }) {
            Text("Perform action 2")
        }

        OutlinedTextField(value = text , onValueChange = { text = it })
    }
}