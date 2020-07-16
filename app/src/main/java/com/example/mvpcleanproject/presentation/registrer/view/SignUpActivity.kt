package com.example.mvpcleanproject.presentation.registrer.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.mvpcleanproject.R
import com.example.mvpcleanproject.base.BaseActivity
import com.example.mvpcleanproject.domain.interactor.registerInteractor.SignUpInteractorImpl
import com.example.mvpcleanproject.presentation.main.view.MainActivity
import com.example.mvpcleanproject.presentation.registrer.RegisterContract
import com.example.mvpcleanproject.presentation.registrer.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.activity_register.*

class SignUpActivity : BaseActivity(), RegisterContract.RegisterView {
    lateinit var presenter : SignUpPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SignUpPresenter(SignUpInteractorImpl())
        presenter.attachView(this)
        btnRegister.setOnClickListener {
            signUp()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun signUp() {
        val name = edtxName.text.toString().trim()
        val email = edtxEmail.text.toString().trim()
        val pass1 = edtxPass1.text.toString().trim()
        val pass2 = edtxPass2.text.toString().trim()
        val confirmPassword = edtxPass2.text.toString().trim()
        if(presenter.checkEmptyName(name))
            edtxName.error = "El nombre es obligatorio."
            return

        if(!presenter.checkValidEmail(email))
            edtxEmail.error = "El email no es valido."
            return

        if(presenter.checkEmptyPassword(pass1, pass2))
            edtxPass1.error = "Este campo es obligatorio."
            edtxPass2.error = "Este campo es obligatorio."
            return

        if(!presenter.checkPasswordMatch(pass1, pass2))
            edtxPass1.error = "Las contraseñas no coinciden."
            edtxPass2.error = "Las contraseñas no coinciden."
            return

        presenter.signUp(name, email, pass1)
    }

    override fun showProgressBar() {
        progressBarRegister.visibility = View.VISIBLE
        btnRegister.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBarRegister.visibility = View.GONE
        btnRegister.visibility = View.VISIBLE
    }

    override fun showError(msgError: String) {
        toast(this, msgError)
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