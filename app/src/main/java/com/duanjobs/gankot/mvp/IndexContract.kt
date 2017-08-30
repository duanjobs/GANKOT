package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.base.BasePresenter
import com.duanjobs.gankot.base.BaseView
import com.duanjobs.gankot.bean.GankArticle

/**
 * Created by duanjobs on 17/8/29.
 */
interface IndexContract {
    //推荐
    interface GankView:BaseView<GankPresenter>
    interface GankPresenter : BasePresenter

    //妹纸
    interface MzView:BaseView<MzPresenter>
    interface MzPresenter: BasePresenter

}