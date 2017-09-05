package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.bean.ListResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by duanjobs on 17/8/29.
 */
class ListPresenter : IndexContract.ListPresenter {

    var mView: IndexContract.ListView? = null
    var mModel: ListModel? = null

    constructor(view: IndexContract.ListView) {
        mView = checkNotNull(view)
        mView!!.setPresenter(this)
        mModel = ListModel.getInstance()
    }

    override fun getData(page: Int, type: String) {
        mModel!!.getData(page, type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result -> Parse(result) }
    }

    private fun Parse(result: ListResult) {
        if (result.error) {

        } else {
            mView!!.setData(result.results)
        }
    }

}