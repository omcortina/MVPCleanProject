package com.example.mvpcleanproject.domain.interactor.auth.passwordrecoveredinteractor

interface PasswordRecoveredInteractor {
    suspend fun sendEmailPasswordReset(email: String)
}