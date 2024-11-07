package com.example.geminichatai.Modules

import com.example.geminichatai.Repositories.RegisterRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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
}