package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.bean.PublishedDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by duanjobs on 17/9/1.
 */
class GankPresenter : IndexContract.GankPresenter {

    var mView: IndexContract.GankView? = null
    var mModel: IndexContract.GankModel? = null

    constructor(view: IndexContract.GankView) {
        mView = checkNotNull(view)
        mView!!.setPresenter(this)
        mModel = GankArticleModel.getInstance()
    }

    override fun getData() {
        mModel!!.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result -> Parse(result) }
    }

    fun Parse(result: PublishedDate) {
        mView!!.setData(result)

    }
}