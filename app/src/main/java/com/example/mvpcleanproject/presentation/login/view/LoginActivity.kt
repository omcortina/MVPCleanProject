package com.example.mvpcleanproject.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.mvpcleanproject.R
import com.example.mvpcleanproject.base.BaseActivity
import com.example.mvpcleanproject.domain.interactor.loginInteractor.SignInInteractorImpl
import com.example.mvpcleanproject.presentation.login.LoginContract
import com.example.mvpcleanproject.presentation.login.presenter.LoginPresenter
import com.example.mvpcleanproject.presentation.main.view.MainActivity
import com.example.mvpcleanproject.presentation.registrer.view.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.LoginView {
    lateinit var presenter : LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(SignInInteractorImpl())
        presenter.attachView(this)
        btnSignIn.setOnClickListener {
            signIn()
        }

        txtRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun showError(msgError: String) {
        toast(this, msgError)
    }

    override fun showProgressBar() {
        progressBarLogin.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBarLogin.visibility = View.GONE
    }

    override fun signIn() {
        val username: String = edtxUsername.text.toString().trim()
        val password: String = edtxPass1.text.toString().trim()
        if (presenter.checkEmptyFields(username, password)) toast(this, "Uno de los campos estan vacíos")
        else
            presenter.signInUserWithEmailAndPassword(username, password)
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
    }
}