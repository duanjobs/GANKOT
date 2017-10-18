package com.duanjobs.gankot.mvp.recommend

import com.duanjobs.gankot.base.BaseListFragment
import com.duanjobs.gankot.bean.Type

/**
 * Created by duanjobs on 17/9/5.
 */
class AndroidFragment : BaseListFragment() {

    override fun getType(): String {
        return return Type.Android.name
    }

    companion object {
        fun getInstance(): AndroidFragment = AndroidFragment()
    }

}