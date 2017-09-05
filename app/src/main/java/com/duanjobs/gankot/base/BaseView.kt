package com.duanjobs.gankot.base


/**
 * Created by duanjobs on 17/8/28.
 */
interface BaseView<T,V> {
    fun setPresenter(presenter: T)
    fun setData(v: V)
}