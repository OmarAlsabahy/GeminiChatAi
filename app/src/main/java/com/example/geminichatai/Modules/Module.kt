package com.example.geminichatai.Modules

import com.example.geminichatai.Repositories.RegisterRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {
    @Provides
    @Singleton
    fun getFirebaseAuth(): FirebaseAuth {
        val auth = Firebase.auth
        return auth
    }
    @Provides
    @Singleton
    fun getFirebaseDatabase():FirebaseDatabase = Firebase.database
}