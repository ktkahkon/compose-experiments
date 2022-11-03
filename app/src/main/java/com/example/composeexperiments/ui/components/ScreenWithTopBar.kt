package com.example.composeexperiments.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScreenWithTopBar(
    title: String,
    onNavigationAction: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable { onNavigationAction() }
                )
            }
        )
        content()
    }
}