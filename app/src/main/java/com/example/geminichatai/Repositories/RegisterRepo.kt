package com.example.geminichatai.Repositories

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegisterRepo @Inject constructor(private val auth:FirebaseAuth) {
    fun register(email:String , password:String, callBack:(Boolean)->Unit){
        auth.createUserWithEmailAndPassword(email , password)
            .addOnSuccessListener {
                callBack.invoke(true)
            }.addOnFailureListener{
                callBack.invoke(false)
            }
    }
}