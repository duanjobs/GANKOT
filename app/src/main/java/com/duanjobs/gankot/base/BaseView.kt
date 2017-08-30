package com.duanjobs.gankot.base

import com.duanjobs.gankot.bean.GankArticle

/**
 * Created by duanjobs on 17/8/28.
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
    fun  setData(results: List<GankArticle>)
}