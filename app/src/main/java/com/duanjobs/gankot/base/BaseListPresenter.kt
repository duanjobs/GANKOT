package com.duanjobs.gankot.base

/**
 * Created by duanjobs on 17/9/1.
 */
interface BaseListPresenter :BasePresenter {
    fun getData(page: Int, type: String)
}