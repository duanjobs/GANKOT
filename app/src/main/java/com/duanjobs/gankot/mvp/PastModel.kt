package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.api.Api
import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 * Created by duanjobs on 17/9/5.
 */
class PastModel:IndexContract.PastModel {
    override fun getData(): Observable<ResponseBody> {
        return Api.Factory.create().getHistory()
    }
    companion object {
        fun getInstance():PastModel = PastModel()
    }
}