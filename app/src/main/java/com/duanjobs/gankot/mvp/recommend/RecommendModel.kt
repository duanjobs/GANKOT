package com.duanjobs.gankot.mvp.recommend

import com.duanjobs.gankot.api.Api
import com.duanjobs.gankot.mvp.IndexContract
import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 * Created by duanjobs on 17/9/1.
 */
class RecommendModel: IndexContract.RecommendModel {
    override fun getData(date: String): Observable<ResponseBody> {
        return Api.Factory.create().getSomeDateData(date)
    }

    companion object {
        fun getInstance(): RecommendModel = RecommendModel()
    }
}