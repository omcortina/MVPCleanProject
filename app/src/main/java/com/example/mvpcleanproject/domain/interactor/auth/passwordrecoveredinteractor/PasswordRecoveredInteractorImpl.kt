package com.example.mvpcleanproject.domain.interactor.auth.passwordrecoveredinteractor

import com.example.mvpcleanproject.presentation.auth.passwordrecovered.exception.PasswordRecoveredException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class PasswordRecoveredInteractorImpl: PasswordRecoveredInteractor {
    override suspend fun sendEmailPasswordReset(email: String): Unit = suspendCancellableCoroutine {continuation ->
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
            if(it.isSuccessful){
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(PasswordRecoveredException(it.exception?.message!!))
            }
        }
    }
}