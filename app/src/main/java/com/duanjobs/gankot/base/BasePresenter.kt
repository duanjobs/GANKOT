package com.duanjobs.gankot.base



/**
 * Created by duanjobs on 17/8/28.
 */
interface  BasePresenter {
    open fun getData(page: Int, type: String)
}