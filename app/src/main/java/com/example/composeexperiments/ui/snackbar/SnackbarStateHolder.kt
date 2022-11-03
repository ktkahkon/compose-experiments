package com.example.composeexperiments.ui.snackbar

import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class Message(
    val type: MessageType,
    val message: String,
    val actionLabel: String,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val onDismiss: ((Message) -> Unit)? = null,
    val onAction: ((Message) -> Unit)? = null
)

enum class MessageType { Success, Info, Error }

class SnackbarStateHolder {

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    fun showMessage(message: Message) {
        _messages.update { it + message }
    }

    fun messageShown(message: Message) {
        val updatedMessages = messages.value.filterNot { it == message }
        _messages.update { updatedMessages }
    }
}