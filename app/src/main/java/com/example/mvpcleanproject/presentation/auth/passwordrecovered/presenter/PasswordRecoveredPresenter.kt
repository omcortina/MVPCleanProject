package com.example.mvpcleanproject.presentation.auth.passwordrecovered.presenter

import com.example.mvpcleanproject.domain.interactor.auth.passwordrecoveredinteractor.PasswordRecoveredInteractor
import com.example.mvpcleanproject.presentation.auth.passwordrecovered.PasswordRecoveredContract
import com.example.mvpcleanproject.presentation.auth.passwordrecovered.exception.PasswordRecoveredException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PasswordRecoveredPresenter (passwordRecoveredInteractor: PasswordRecoveredInteractor): PasswordRecoveredContract.PasswordRecoveredPresenter, CoroutineScope{
    var view: PasswordRecoveredContract.PasswordRecoveredView? = null
    var passwordRecoveredInteractor: PasswordRecoveredInteractor? = null

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.passwordRecoveredInteractor = passwordRecoveredInteractor
    }

    override fun attachView(passwordRecoveredView: PasswordRecoveredContract.PasswordRecoveredView) {
        this.view = passwordRecoveredView
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun sendEmailPasswordReset(email: String) {
        launch {
            try {
                view?.showProgressBarPasswordRecovered()
                passwordRecoveredInteractor?.sendEmailPasswordReset(email)
                if(isViewAttached()){
                    view?.hideProgressBarPasswordRecovered()
                    view?.navigateToLogin()
                }
            }catch (e: PasswordRecoveredException){
                view?.showError(e?.message)
                view?.hideProgressBarPasswordRecovered()
            }
        }
    }

}