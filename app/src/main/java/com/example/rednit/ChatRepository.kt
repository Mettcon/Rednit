package com.example.rednit

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class ChatRepository(private val database: FirebaseDatabase) {
    private val chatMessagesReference = database.getReference("chats")

    fun sendMessage(senderId: String, messageText: String, receiverId: String) {
        val id = chatMessagesReference.push().key
        val chatMessage = id?.let {
            ChatMessage(
                id = it,
                senderId = senderId,
                messageText = messageText,
                timestamp = System.currentTimeMillis(),
                receiverId = receiverId
            )
        }

        chatMessage?.let {
            chatMessagesReference.child(id).setValue(it)
        }
    }

    fun receiveMessages(onMessageReceived: (ChatMessage) -> Unit) {
        chatMessagesReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(ChatMessage::class.java)
                chatMessage?.let {
                    onMessageReceived(it)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        )
    }
}