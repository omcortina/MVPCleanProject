package com.example.mvpcleanproject.domain.interactor.registerInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUpInteractorImpl: SignUpInteractor {
    override fun signUp(name: String, email: String, password: String, listener: SignUpInteractor.RegisterCallback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener{ itSignUp ->
            if(itSignUp.isSuccessful){
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()

                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener{
                    if(it.isSuccessful){
                        listener.onRegisterSuccess()
                    }
                }
            }else{
                listener.onRegisterFailure(itSignUp.exception?.message.toString())
            }
        }
    }
}