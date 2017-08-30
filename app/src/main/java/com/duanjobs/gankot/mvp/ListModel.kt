package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.api.Api
import com.duanjobs.gankot.base.BaseListModel
import com.duanjobs.gankot.bean.ListResult
import io.reactivex.Observable

/**
 * Created by duanjobs on 17/8/30.
 */
class ListModel:BaseListModel {
    override fun getData(page: Int, type: String): Observable<ListResult> {
         return Api.Factory.create().getContent(type,page)
    }

    companion object {
        fun getInstance():ListModel = ListModel()
    }
}