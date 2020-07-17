package com.example.mvpcleanproject.domain.interactor.auth.registerInteractor

interface SignUpInteractor {
    interface RegisterCallback {
        fun onRegisterSuccess()
        fun onRegisterFailure(errorMsg: String)
    }

    fun signUp(name: String, email: String, password: String, listener: RegisterCallback)
}