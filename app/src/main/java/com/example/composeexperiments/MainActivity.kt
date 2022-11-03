package com.example.composeexperiments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeexperiments.navigation.MainNavGraph
import com.example.composeexperiments.navigation.navigateTo
import com.example.composeexperiments.ui.bottombar.BottomNavigationBar
import com.example.composeexperiments.ui.snackbar.SnackbarStateHolder
import com.example.composeexperiments.ui.theme.ComposeExperimentsTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExperimentsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TestApp()
                }
            }
        }
    }
}

@Composable
fun TestApp(
    snackbarStateHolder: SnackbarStateHolder = get()
) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val messages by snackbarStateHolder.messages.collectAsState()

    messages.firstOrNull()?.let {
        LaunchedEffect(it) {
            when (snackbarHostState.showSnackbar(it.message, actionLabel = it.actionLabel, duration = it.duration)) {
                SnackbarResult.Dismissed -> it.onDismiss?.let { onDismiss -> onDismiss(it) }
                SnackbarResult.ActionPerformed -> it.onAction?.let { onAction -> onAction(it) }
            }
            snackbarStateHolder.messageShown(it)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = navBackStackEntry?.destination?.route ?: "",
                navigateTo = navController::navigateTo
            )
        }
    ) {
        MainNavGraph(navController)
    }
}