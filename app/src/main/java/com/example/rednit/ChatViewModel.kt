package com.example.rednit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {
    private val _messages = MutableLiveData<List<ChatMessage>>()
    val messages: LiveData<List<ChatMessage>> = _messages

    init {
        repository.receiveMessages { message ->
            _messages.value = _messages.value.orEmpty() + message
        }
    }

    fun sendMessage(messageText: String, senderId: String, receiverId: String) {
        repository.sendMessage(senderId, messageText, receiverId)
    }
}
