package com.example.mvpcleanproject.domain.interactor.loginInteractor

import com.google.firebase.auth.FirebaseAuth

class SignInInteractorImpl: SignInInteractor {
    override fun singInWithUsernameAndPassword(username: String, password: String, listener: SignInInteractor.SignInCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password).addOnCompleteListener{
            if (it.isSuccessful)
                listener.onSignInSuccess()
            else
                listener.onSignInFailure(it.exception?.message!!)
        }
    }
}