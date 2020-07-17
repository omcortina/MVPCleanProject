package com.example.mvpcleanproject.domain.interactor.auth.loginInteractor

import com.example.mvpcleanproject.presentation.auth.login.exception.FirebaseLoginException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SignInInteractorImpl: SignInInteractor {
    override suspend fun singInWithUsernameAndPassword(username: String, password: String): Unit = suspendCancellableCoroutine {continuation ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password).addOnCompleteListener{
            if(it.isSuccessful){
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(FirebaseLoginException(it.exception?.message))
            }
        }
    }
}