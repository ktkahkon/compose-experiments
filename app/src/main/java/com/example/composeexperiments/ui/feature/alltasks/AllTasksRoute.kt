package com.example.composeexperiments.ui.feature.alltasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeexperiments.data.Task
import com.example.composeexperiments.navigation.NavigationTarget
import com.example.composeexperiments.ui.feature.task.taskpage.TaskNavigationRoute
import kotlinx.coroutines.flow.MutableSharedFlow

//@Composable
//fun AllTasksRoute(
//    navigateTo: (NavigationTarget) -> Unit
//) {
//    val viewModel: AllTasksViewModel = getViewModel()
//    val state = viewModel.state()
//
//    AllTasksScreen(
//        state = state,
//        handleEvent = viewModel::handleEvent,
//        navigateTo = navigateTo
//    )
//}


@Composable
fun AllTasksRoute(
    navigateTo: (NavigationTarget) -> Unit
) {
    val events: MutableSharedFlow<AllTasksEvent> = remember { MutableSharedFlow(extraBufferCapacity = 5) }
    val state = AllTasksPresenter(events = events)

    AllTasksScreen(
        state = state,
        handleEvent = { event -> events.tryEmit(event) },
        navigateTo = navigateTo
    )
}

@Composable
fun AllTasksScreen(
    state: AllTaskState,
    handleEvent: (AllTasksEvent) -> Unit,
    navigateTo: (NavigationTarget) -> Unit
) {
    Column(Modifier.padding(16.dp)) {
        when (state) {
            is AllTaskState.ShowTasks -> {
                OutlinedButton(onClick = { handleEvent(AllTasksEvent.ShowSnackbar) }) {
                    Text("Show snackbar")
                }

                OutlinedTextField(value = state.text, onValueChange = {
                    handleEvent(AllTasksEvent.UpdateText(it))
                })

                TaskList(state.tasks, navigateTo)
            }

            AllTaskState.Loading -> {
                Text("Loading")
            }
        }
    }
}

@Composable
private fun TaskList(
    tasks: List<Task>,
    navigateTo: (NavigationTarget) -> Unit
) {
    tasks.forEach {
        Card(modifier = Modifier
            .padding(top = 8.dp)
            .clickable { navigateTo(TaskNavigationRoute(it.id)) }
        ) {
            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(it.title)
            }
        }
    }
}