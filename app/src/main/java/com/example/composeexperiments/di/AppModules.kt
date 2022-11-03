package com.example.composeexperiments.di

import com.example.composeexperiments.data.Repository
import com.example.composeexperiments.ui.feature.alltasks.AllTasksViewModel
import com.example.composeexperiments.ui.feature.task.taskpage.TaskViewModel
import com.example.composeexperiments.ui.snackbar.SnackbarStateHolder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { AllTasksViewModel(get(), get()) }
    viewModel { TaskViewModel(get()) }

    single { Repository() }
    single { SnackbarStateHolder() }
}