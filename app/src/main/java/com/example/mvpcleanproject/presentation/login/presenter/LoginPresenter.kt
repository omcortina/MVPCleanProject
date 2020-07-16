package com.example.mvpcleanproject.presentation.login.presenter

import com.example.mvpcleanproject.domain.interactor.loginInteractor.SignInInteractor
import com.example.mvpcleanproject.presentation.login.LoginContract

class LoginPresenter(signInInteractor: SignInInteractor): LoginContract.LoginPresenter {

    var view: LoginContract.LoginView? = null
    var signInInteractor: SignInInteractor? = null
    init {
        this.signInInteractor = signInInteractor
    }
    override fun attachView(view: LoginContract.LoginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun signInUserWithEmailAndPassword(username: String, password: String) {
        signInInteractor?.singInWithUsernameAndPassword(username, password, object: SignInInteractor.SignInCallback{
            override fun onSignInSuccess() {
                if(isViewAttached())
                    view?.hideProgressBar()
                    view?.navigateToMain()
            }

            override fun onSignInFailure(errorMesg: String) {
                view?.hideProgressBar()
                view?.showError(errorMesg)
            }
        })
    }

    override fun checkEmptyFields(username: String, password: String): Boolean {
        return username.isEmpty() || password.isEmpty()
    }
}