package com.example.rednit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    // State to store and update the list of messages
    val messages by viewModel.messages.observeAsState(listOf())
    // State for the current input in the TextField
    var currentMessage by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize()) {
        // List of chat messages
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageItem(message)
            }
        }

        // Message input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), //TODO
            horizontalArrangement = Arrangement.SpaceBetween //TODO
        ) {
            TextField(
                value = currentMessage,
                onValueChange = { currentMessage = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Some placeholder text") }
            )
            Spacer(modifier = Modifier.width(8.dp)) //TODO
            Button(onClick = {
                if (currentMessage.isNotBlank()) {
                    //TODO Replacemnts of ID
                    viewModel.sendMessage(currentMessage, "UsersSenderId", "ReceiverID")
                    currentMessage = ""
                }
            }
            ) {
                Text("Send")
            }
        }
    }
}


@Composable
fun MessageItem(chatMessage: ChatMessage) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            Text("${chatMessage.senderId}: ${chatMessage.messageText}" )
        }
    }
}