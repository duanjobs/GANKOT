package com.duanjobs.gankot.mvp

import android.os.Bundle
import android.support.v4.app.FragmentManager

import com.duanjobs.gankot.R
import com.duanjobs.gankot.base.BaseActivity
import com.duanjobs.gankot.base.BaseFragment

class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseActivity() {
    var mMeiZhiPresenter: IndexContract.MzPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var meizi = MeiZhiContainerFragment.getInstance()
        mMeiZhiPresenter = MeiZhiPresenter(meizi)
        if (savedInstanceState == null) {
            initFragment(meizi)
        }

    }

    private fun initFragment(to: BaseFragment) {
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().add(R.id.container, to).commit()
    }
}

