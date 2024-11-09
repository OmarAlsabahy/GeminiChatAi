package com.example.geminichatai.Repositories

import com.example.geminichatai.Models.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class ChatRepo @Inject constructor(private val database: FirebaseDatabase , private val auth: FirebaseAuth) {
    private val reference = database.reference
    private val apiKey = "AIzaSyCbfBRD4WZyt4QOer0q6alEMy25SowIXtM"
   suspend fun sendMessage(prompt:String){
       val generativeModel = GenerativeModel(
           modelName = "gemini-1.5-flash",
           apiKey = apiKey
       )
       val response = generativeModel.generateContent(prompt)



       val message = MessageModel(
           message = prompt,
           userId = auth.currentUser!!.uid,
           isFromUser = true
       )


       val responseModel = MessageModel(
           message = response.text.toString(),
           userId = auth.currentUser!!.uid,
           isFromUser = false
       )
        reference.child("messages").child(auth.currentUser!!.uid).push().setValue(message)
       reference.child("messages").child(auth.currentUser!!.uid).push().setValue(responseModel)
    }


    fun getAllMessages(callBack : (List<MessageModel>)->Unit){
        val readReference = reference.child("messages").child(auth.currentUser!!.uid)
        val messages = mutableListOf<MessageModel>()
        readReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messages.clear()
                for(messageSnapshot in snapshot.children){

                    val message = messageSnapshot.getValue(MessageModel::class.java)
                    messages.add(message!!)
                }

                callBack.invoke(messages)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}