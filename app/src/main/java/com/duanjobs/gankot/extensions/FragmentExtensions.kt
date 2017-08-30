package com.duanjobs.gankot.extensions

import com.duanjobs.gankot.base.BaseActivity
import com.duanjobs.gankot.base.BaseFragment
import org.jetbrains.anko.support.v4.act

/**
 * Created by duanjobs on 17/8/29.
 */
 fun BaseFragment.loading(msg:Int) = (act as BaseActivity).loading(msg)