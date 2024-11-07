package com.example.geminichatai.Repositories

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepo @Inject constructor(private val auth: FirebaseAuth) {
    fun login(email: String, password: String, callback: (Boolean) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                callback.invoke(true)
            }.addOnFailureListener{
                callback.invoke(false)
            }
    }
}