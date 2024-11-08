package com.example.geminichatai.Repositories

import com.example.geminichatai.Models.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
}