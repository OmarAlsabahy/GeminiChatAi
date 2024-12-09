package com.example.geminichatai.Models

data class MessageModel(
    val message : String = "",
    val userId:String= "",
    val fromUser:Boolean = false
)