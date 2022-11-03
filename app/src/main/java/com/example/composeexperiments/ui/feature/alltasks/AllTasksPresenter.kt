package com.example.composeexperiments.ui.feature.alltasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.composeexperiments.data.Repository
import com.example.composeexperiments.ui.snackbar.Message
import com.example.composeexperiments.ui.snackbar.MessageType
import com.example.composeexperiments.ui.snackbar.SnackbarStateHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get

// Experiment to handle screen level state in a composable instead of using a viewmodel. Inspired by Molecule (https://github.com/cashapp/molecule)

@Composable
fun AllTasksPresenter(
    events: Flow<AllTasksEvent>,
    repository: Repository = get(),
    snackbarStateHolder: SnackbarStateHolder = get()
): AllTaskState {
    var text by rememberSaveable { mutableStateOf("") }
    var loading by rememberSaveable { mutableStateOf(true) }
    val work by repository.observeAllTasks().collectAsState(emptyList())

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                AllTasksEvent.ShowSnackbar -> {
                    snackbarStateHolder.showMessage(Message(MessageType.Info, message = "Test", actionLabel = "OK"))
                }

                is AllTasksEvent.UpdateText -> {
                    text = event.text
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(1000)
        loading = false
    }

    return if (loading) {
        AllTaskState.Loading
    } else {
        AllTaskState.ShowTasks(text, work)
    }
}