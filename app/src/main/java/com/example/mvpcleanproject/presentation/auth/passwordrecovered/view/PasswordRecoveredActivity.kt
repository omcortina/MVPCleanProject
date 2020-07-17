package com.example.mvpcleanproject.presentation.auth.passwordrecovered.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvpcleanproject.R
import com.example.mvpcleanproject.base.BaseActivity
import com.example.mvpcleanproject.domain.interactor.auth.passwordrecoveredinteractor.PasswordRecoveredInteractorImpl
import com.example.mvpcleanproject.presentation.auth.login.view.LoginActivity
import com.example.mvpcleanproject.presentation.auth.passwordrecovered.PasswordRecoveredContract
import com.example.mvpcleanproject.presentation.auth.passwordrecovered.presenter.PasswordRecoveredPresenter
import kotlinx.android.synthetic.main.activity_password_recovered.*

class PasswordRecoveredActivity : BaseActivity(), PasswordRecoveredContract.PasswordRecoveredView {
    lateinit var presenter: PasswordRecoveredPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PasswordRecoveredPresenter(PasswordRecoveredInteractorImpl())
        presenter.attachView(this)
        btnSendEmail.setOnClickListener {
            navigateToLogin()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_password_recovered
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)
    }

    override fun showProgressBarPasswordRecovered() {
        progressBarRecovered.visibility = View.VISIBLE
        btnSendEmail.visibility = View.GONE
    }

    override fun hideProgressBarPasswordRecovered() {
        progressBarRecovered.visibility = View.GONE
        btnSendEmail.visibility = View.VISIBLE
    }

    override fun passwordRecovered() {
        val email: String = edtxEmailRecovered.text.toString().trim()
        presenter.sendEmailPasswordReset(email)
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }
}