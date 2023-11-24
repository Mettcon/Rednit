package com.example.rednit

data class ChatMessage(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val messageText: String,
    val timestamp: Long
)
