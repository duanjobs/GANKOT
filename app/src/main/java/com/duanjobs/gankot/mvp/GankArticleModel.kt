package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.api.Api
import com.duanjobs.gankot.bean.PublishedDate
import io.reactivex.Observable

/**
 * Created by duanjobs on 17/9/1.
 */
class GankArticleModel:IndexContract.GankModel {
    override fun getData(): Observable<PublishedDate> {
        return Api.Factory.create().getPublishedDate()
    }

    companion object {
        fun getInstance():GankArticleModel = GankArticleModel()
    }
}