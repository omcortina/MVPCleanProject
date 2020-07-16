package com.example.mvpcleanproject.presentation.login

interface LoginContract {
    interface LoginView{
        fun showError(msgError:String)
        fun showProgressBar()
        fun hideProgressBar()
        fun signIn()
        fun navigateToMain()
    }

    interface LoginPresenter{
        fun attachView(view: LoginView)
        fun dettachView()
        fun isViewAttached(): Boolean
        fun signInUserWithEmailAndPassword(username: String, password: String)
        fun checkEmptyFields(username: String, password: String): Boolean
    }
}