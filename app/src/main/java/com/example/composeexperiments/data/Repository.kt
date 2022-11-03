package com.example.composeexperiments.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull

class Repository {
    private val tasks: MutableStateFlow<List<Task>> = MutableStateFlow(
        listOf(
            Task(
                id = "1",
                title = "Task 1",
            ),
            Task(
                id = "2",
                title = "Task 2",
            )
        )
    )

    fun observeAllTasks(): Flow<List<Task>> {
        return tasks
    }

    fun observeTask(id: String): Flow<Task> {
        return tasks.mapNotNull { it.find { work -> work.id == id } }
    }
}

//class TasksDataSource() {
//
//}


data class Task(
    val id: String,
    val title: String,
)
