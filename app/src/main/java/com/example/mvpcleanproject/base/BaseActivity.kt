package com.example.mvpcleanproject.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(getLayout())
    }

    @LayoutRes
    abstract fun getLayout(): Int

    fun Context.toast(context: Context = applicationContext, message: String?, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message , duration).show()
    }
}