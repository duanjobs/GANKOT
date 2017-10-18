package com.duanjobs.gankot.mvp.recommend

import com.duanjobs.gankot.base.BaseListFragment
import com.duanjobs.gankot.bean.Type

/**
 * Created by duanjobs on 17/9/5.
 */
class TuoZhanFragment : BaseListFragment() {

    override fun getType(): String {
        return return Type.拓展资源.name
    }

    companion object {
        fun getInstance(): TuoZhanFragment = TuoZhanFragment()
    }

}