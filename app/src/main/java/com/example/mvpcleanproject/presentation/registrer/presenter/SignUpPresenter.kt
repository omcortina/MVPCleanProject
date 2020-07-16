package com.example.mvpcleanproject.presentation.registrer.presenter

import androidx.core.util.PatternsCompat
import com.example.mvpcleanproject.domain.interactor.registerInteractor.SignUpInteractor
import com.example.mvpcleanproject.presentation.registrer.RegisterContract

class SignUpPresenter(signUpInteractor: SignUpInteractor): RegisterContract.RegisterPresenter {
    var view: RegisterContract.RegisterView? = null
    var signUpInteractor: SignUpInteractor? = null

    init{
        this.signUpInteractor = signUpInteractor
    }

    override fun attachView(view: RegisterContract.RegisterView) {
        this.view = view
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun dettachView() {
        view = null
    }

    override fun checkEmptyName(fullname: String): Boolean {
        return fullname.isEmpty()
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPassword(pass1: String, pass2: String): Boolean {
        return pass1.isEmpty() || pass2.isEmpty()
    }

    override fun checkPasswordMatch(pass1: String, pass2: String): Boolean {
        return pass1.equals(pass2)
    }

    override fun signUp(name: String, email: String, password: String) {
        view?.showProgressBar()
        signUpInteractor?.signUp(name, email, password, object: SignUpInteractor.RegisterCallback{
            override fun onRegisterSuccess() {
                if (isViewAttached())
                    view?.navigateToMain()
                    view?.hideProgressBar()
            }

            override fun onRegisterFailure(errorMsg: String) {
                view?.hideProgressBar()
                view?.showError(errorMsg)
            }

        })
    }

}