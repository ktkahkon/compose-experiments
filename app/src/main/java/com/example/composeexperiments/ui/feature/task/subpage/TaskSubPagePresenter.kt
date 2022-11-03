package com.example.composeexperiments.ui.feature.task.subpage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

data class TaskSubPageState(val title: String)

@Composable
fun TaskSubPagePresenter(taskId: String): TaskSubPageState {

    var title by remember { mutableStateOf("Loading") }

    LaunchedEffect(Unit) {
        delay(1000)
        if (taskId == "1") {
            title = "Task $taskId - Foo"
        } else {
            title = "Task $taskId - Bar"
        }
    }

    return TaskSubPageState(title)
}