package com.duanjobs.gankot.extensions

import com.duanjobs.gankot.base.BaseActivity

/**
 * Created by duanjobs on 17/8/28.
 */

fun BaseActivity.loading(msg:Int) = handler.sendEmptyMessage(msg)