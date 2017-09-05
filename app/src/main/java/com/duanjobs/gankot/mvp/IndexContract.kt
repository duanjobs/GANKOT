package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.base.BaseListPresenter
import com.duanjobs.gankot.base.BasePresenter
import com.duanjobs.gankot.base.BaseView
import com.duanjobs.gankot.bean.GankArticle
import com.duanjobs.gankot.bean.History
import com.duanjobs.gankot.bean.PublishedDate
import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 * Created by duanjobs on 17/8/29.
 */
interface IndexContract {
    //推荐 父fragment
    interface GankView:BaseView<GankPresenter,PublishedDate>
    interface GankPresenter : BasePresenter{
        fun getData()
    }
    interface GankModel{
        fun getData():Observable<PublishedDate>
    }

    interface RecommendView:BaseView<RecommendPresenter,ResponseBody>
    interface RecommendPresenter:BasePresenter{
        fun getData(date:String)
    }

    interface RecommendModel{
        fun getData(date:String):Observable<ResponseBody>
    }

    // list
    interface ListView:BaseView<ListPresenter,List<GankArticle>>
    interface ListPresenter: BaseListPresenter

    //history
    interface PastView:BaseView<PastPresenter,List<History>>
    interface PastPresenter:BasePresenter{
        fun getData()
    }
    interface PastModel{
        fun getData():Observable<ResponseBody>
    }

}