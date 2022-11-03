package com.example.composeexperiments.network

import com.example.composeexperiments.data.Task

class ClientApi {

    suspend fun getTasks(): List<Task> {
        return listOf(
            Task(
                id = "1",
                title = "Task 1",
            ),
            Task(
                id = "2",
                title = "Task 2",
            )
        )
    }
}