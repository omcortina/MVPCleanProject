package com.example.mvpcleanproject.domain.interactor.auth.loginInteractor

interface SignInInteractor {
    suspend fun singInWithUsernameAndPassword(username: String, password: String)
}