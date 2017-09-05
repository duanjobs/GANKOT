package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.base.BaseListFragment
import com.duanjobs.gankot.bean.Type

/**
 * Created by duanjobs on 17/9/5.
 */
class VideoFragment : BaseListFragment() {

    override fun getType(): String {
        return return Type.休息视频.name
    }

    companion object {
        fun getInstance():  VideoFragment =  VideoFragment()
    }

}