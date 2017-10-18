package com.duanjobs.gankot.mvp.recommend

import com.duanjobs.gankot.mvp.IndexContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

/**
 * Created by duanjobs on 17/9/1.
 */
class RecommendPresenter : IndexContract.RecommendPresenter {

    var mView: IndexContract.RecommendView? = null
    var mModel: IndexContract.RecommendModel? = null

    constructor(view: IndexContract.RecommendView) {
        mView = checkNotNull(view)
        mView!!.setPresenter(this)
        mModel = RecommendModel.getInstance()
    }

    override fun getData(date: String) {
        mModel!!.getData(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result -> Parse(result) }}

    fun Parse(result: ResponseBody) {
        mView!!.setData(result)

    }
}