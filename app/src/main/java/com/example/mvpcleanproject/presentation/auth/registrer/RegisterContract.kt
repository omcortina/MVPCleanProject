package com.example.mvpcleanproject.presentation.auth.registrer

interface RegisterContract {
    interface RegisterView{
        fun navigateToMain()

        fun signUp()
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(msgError:String)
    }

    interface RegisterPresenter{
        fun attachView(view: RegisterView)
        fun dettachView()

        fun isViewAttached(): Boolean
        fun checkEmptyName(fullname: String): Boolean
        fun checkValidEmail(email: String): Boolean
        fun checkEmptyPassword(pass1: String, pass2: String): Boolean //Si las password estan vacias
        fun checkPasswordMatch(pass1: String, pass2: String): Boolean //Si las password coinciden
        fun signUp(fullname: String, email: String, password: String)
    }
}