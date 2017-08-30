package com.duanjobs.gankot.base

import com.duanjobs.gankot.bean.ListResult
import io.reactivex.Observable

/**
 * Created by duanjobs on 17/8/30.
 */
interface BaseListModel{
    fun getData(page: Int,type:String): Observable<ListResult>
}