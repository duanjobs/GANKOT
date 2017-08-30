package com.duanjobs.gankot.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment

/**
 * Created by duanjobs on 17/8/29.
 */
abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int
    private val SAVED_IS_HIDDEN: String ="SAVED_IS_HIDDEN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState!=null){
            val isHiddenBefore: Boolean = savedInstanceState.getBoolean(SAVED_IS_HIDDEN)
            val ft = fragmentManager.beginTransaction()
            if (isHiddenBefore){
                ft.hide(this)
            }else {
                ft.show(this)
            }
            ft.commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putBoolean(SAVED_IS_HIDDEN,isHidden)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}