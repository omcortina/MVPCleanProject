package com.example.mvpcleanproject.presentation.auth.passwordrecovered

interface PasswordRecoveredContract {
    interface PasswordRecoveredView{
        fun showError(msgError: String?)
        fun showProgressBarPasswordRecovered()
        fun hideProgressBarPasswordRecovered()
        fun passwordRecovered()
        fun navigateToLogin()
    }

    interface PasswordRecoveredPresenter{
        fun attachView(passwordRecoveredView: PasswordRecoveredView)
        fun isViewAttached(): Boolean
        fun dettachView()
        fun dettachJob()
        fun sendEmailPasswordReset(email: String)
    }
}