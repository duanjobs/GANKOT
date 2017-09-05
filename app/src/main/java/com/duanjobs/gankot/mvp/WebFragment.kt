package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.base.BaseListFragment
import com.duanjobs.gankot.bean.Type

/**
 * Created by duanjobs on 17/9/5.
 */
class WebFragment : BaseListFragment() {

    override fun getType(): String {
        return return Type.前端.name
    }

    companion object {
        fun getInstance(): WebFragment = WebFragment()
    }

}