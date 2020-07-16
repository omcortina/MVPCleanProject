package com.example.mvpcleanproject.domain.interactor.loginInteractor

interface SignInInteractor {
    interface SignInCallback{
        fun onSignInSuccess()
        fun onSignInFailure(errorMesg: String)
    }

    fun singInWithUsernameAndPassword(username: String, password: String, listener: SignInCallback)
}