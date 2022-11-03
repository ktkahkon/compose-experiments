package com.example.composeexperiments.ui.feature.task.taskpage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class TaskState(
    val taskId: String
)

sealed interface TaskEvent {
    object PerformAction : TaskEvent
    data class PerformAnotherAction(val value: String) : TaskEvent
}

class TaskViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(TaskState(
        taskId = checkNotNull(savedStateHandle["taskId"])
    ))
    val state: StateFlow<TaskState> = _state

    private val events: MutableSharedFlow<TaskEvent> = MutableSharedFlow(extraBufferCapacity = 5)

    fun handleEvent(event: TaskEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {
        viewModelScope.launch {
            events.collect {
                when (it) {
                    TaskEvent.PerformAction -> {
                        // TODO
                    }
                    is TaskEvent.PerformAnotherAction -> {
                        // TODO
                    }
                }
            }
        }
    }

}