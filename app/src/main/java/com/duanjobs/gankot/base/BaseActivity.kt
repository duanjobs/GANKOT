package com.duanjobs.gankot.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.duanjobs.gankot.R
import com.duanjobs.gankot.ui.LoadDialog
import com.duanjobs.gankot.utils.Const

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int
    var loadingDialog: LoadDialog? = null

    open var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (!Thread.currentThread().isInterrupted) {
                when (msg.what) {
                    Const.SHOW -> loadingDialog!!.show()
                    Const.DISMISS -> loadingDialog!!.hide()
                }
            }
            super.handleMessage(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        loadingDialog = LoadDialog(this)
    }

    override fun onStop() {
        super.onStop()
        loadingDialog!!.dismiss()
    }

}
