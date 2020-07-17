package com.example.mvpcleanproject.presentation.auth.login.presenter

import com.example.mvpcleanproject.domain.interactor.auth.loginInteractor.SignInInteractor
import com.example.mvpcleanproject.presentation.auth.login.LoginContract
import com.example.mvpcleanproject.presentation.auth.login.exception.FirebaseLoginException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginPresenter(signInInteractor: SignInInteractor): LoginContract.LoginPresenter, CoroutineScope {

    var view: LoginContract.LoginView? = null
    var signInInteractor: SignInInteractor? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.signInInteractor = signInInteractor
    }
    override fun attachView(view: LoginContract.LoginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun signInUserWithEmailAndPassword(username: String, password: String) {
        launch {
            view?.showProgressBar()
            try {
                signInInteractor?.singInWithUsernameAndPassword(username, password)
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            } catch (e: FirebaseLoginException){
                view?.showError(e?.message)
                view?.hideProgressBar()
            }

        }
    }

    override fun checkEmptyFields(username: String, password: String): Boolean {
        return username.isEmpty() || password.isEmpty()
    }
}