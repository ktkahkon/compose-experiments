package com.example.composeexperiments.ui.feature.alltasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexperiments.data.Repository
import com.example.composeexperiments.data.Task
import com.example.composeexperiments.ui.snackbar.Message
import com.example.composeexperiments.ui.snackbar.MessageType
import com.example.composeexperiments.ui.snackbar.SnackbarStateHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

sealed interface AllTasksEvent {
    data class UpdateText(val text: String) : AllTasksEvent
    object ShowSnackbar : AllTasksEvent
}

sealed interface AllTaskState {
    object Loading : AllTaskState
    data class ShowTasks(val text: String, val tasks: List<Task>) : AllTaskState
}

// Experiment to combine using ViewModel and building state like in AllTasksPresenter

class AllTasksViewModel(
    internal val repository: Repository,
    internal val snackbarStateHolder: SnackbarStateHolder
) : ViewModel() {
    internal var text by mutableStateOf("")
    internal var loading by mutableStateOf(true)

    internal val events: MutableSharedFlow<AllTasksEvent> = MutableSharedFlow(extraBufferCapacity = 5)

    init {
        viewModelScope.launch {
            delay(2000)
            loading = false
        }
    }

    fun handleEvent(event: AllTasksEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }
}

@Composable
fun AllTasksViewModel.state(): AllTaskState {
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

    return if (loading) {
        AllTaskState.Loading
    } else {
        AllTaskState.ShowTasks(text, work)
    }
}